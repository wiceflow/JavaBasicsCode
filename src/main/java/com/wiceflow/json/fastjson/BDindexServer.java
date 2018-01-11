package com.wiceflow.json.fastjson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiceflow.frame.quartz2.RunJob;
import com.wiceflow.http.retrofit2.IndexSystemHttpJSON;
import com.wiceflow.json.fastjson.po.BasicCTwo;
import com.wiceflow.json.fastjson.po.General;
import com.wiceflow.json.fastjson.po.TableForJSON;
import com.wiceflow.json.fastjson.po.table;
import com.wiceflow.util.QuartzUtil;
import okhttp3.ResponseBody;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import static com.wiceflow.json.fastjson.po.ArrayTurnPO.trun;
import static com.wiceflow.util.RetrofitUtil.getCallResponseBody;

/**
 * Created by BF on 2017/12/11.
 * 北斗--指数系统优化
 * 加入定时器
 */
public class BDindexServer {
    // 日志
    private static final Logger _LOG = LoggerFactory.getLogger(BDindexServer.class);
    private static Configuration cfg = new Configuration().configure();
    private static SessionFactory sessionFactory = cfg.buildSessionFactory();
    /**
     * 还可以进一步封装
     *
     * @param date 发送请求中要设为final
     * @param flag
     */
    public void getData(final String date, final Boolean flag) {
        String url = "http://202.104.124.214:13401/";
        Call<ResponseBody> call = getCallResponseBody(url, IndexSystemHttpJSON.class, date);
        // 发送网络请求---异步
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject json = JSON.parseObject(new String(response.body().bytes()));
                    JSONObject json1 = json.getJSONObject("table");
                    table table = JSON.parseObject(json1.toJSONString(), table.class);
                    TableForJSON t = trun(table);
                    JSONObject json2 = json.getJSONObject("general");
                    General genera = JSON.parseObject(json2.toJSONString(), General.class);
                    BasicCTwo basic = new BasicCTwo();

                    basic.setGeneral(genera);
                    basic.setTfj(t);
                    basic.setPeriod(t.getPeriod());
                    basic.setDate(date);
                    if (flag) {
                        saveDB(basic);
                        System.out.println("日期为：" + basic.getDate() + "的数据为：" + basic.toString());
                    }
                    //ObtainData();
                } catch (Exception e) {
                    _LOG.error("newString转换字符串出错");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                _LOG.error("解析网络接口数据出错");
                throwable.getMessage();
            }
        });
    }

    /**
     * 时间调度
     *
     * @throws SchedulerException
     */
    // TODO 在部署的时候修改TIME 使其可传入
     public void timer(String time) throws SchedulerException {
        time = "0 0 20 ? * 6";
        QuartzUtil quartzUtil = QuartzUtil.getInstance();
        // 通过过JobDetail封装RunJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
        JobDetail job = quartzUtil.getJobDetail(RunJob.class, "job1", "group1");
        // 创建一个CronScheduleBuilder 实例，指定该Trigger在Scheduler中所属组及名称。
        // 每周五晚上8点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
        // 设置调度的时间规则
        Trigger trigger = quartzUtil.getTrigger("trigger1", "griop1", cronScheduleBuilder);
        // 注册
        quartzUtil.scheduleJob(job, trigger);
        // 启动
        quartzUtil.startSchedule();
    }

    /**
     * 私有方法 不允许外部直接调用此方法
     * @param basic
     */
    private void saveDB(BasicCTwo basic){
        Session session = null;
        try {
            // 开启hibernate session
            session = sessionFactory.openSession();
            // 开启事务
            session.beginTransaction();
            session.save(basic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session!=null) {
                session.flush();
                session.close();
            }
        }
    }

    /**
     * 根据期数来查询
     * 期数可能重复，返回最新的一期
     * @param period [String] 期数 50
     * @return
     */
    public BasicCTwo queryByPeriod(String period) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria c = session.createCriteria(BasicCTwo.class);
            c.add(Restrictions.eq("period", period));

            List<BasicCTwo> bv = c.list();
            if (bv==null||bv.size()==0){
                _LOG.error("输入期数为空，请重新输入");
                return null;
            }
            BasicCTwo basicCTwo = bv.get(bv.size()-1);
            basicCTwo.toString();
            return basicCTwo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * 根据日期来查询
     * @param date [String] 20171212
     * @return
     */
    public  BasicCTwo queryByDate(String date) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria c = session.createCriteria(BasicCTwo.class);
            c.add(Restrictions.eq("date", date));

            List<BasicCTwo> bv = c.list();
            //BasicCTwo b = (BasicCTwo) c.uniqueResult();
            if (bv.size()==0||bv==null){
                _LOG.error("日期输入错误，查询为空");
                return null;
            }
            bv.toString();
            return bv.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * 查询最新一期的数据
     * @return
     */
    public  BasicCTwo queryByNew() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            BasicCTwo basicCTwo =
                    (BasicCTwo) session
                            .createQuery
                                    ("select ap from BasicCTwo as ap where ap.id = (select  max(bean.id) from BasicCTwo bean ) ")
                            .uniqueResult();
            // 防止懒加载数据不加载进去
            basicCTwo.toString();
            return basicCTwo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
