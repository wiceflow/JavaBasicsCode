package com.wiceflow.polypeptide;

/**
 * @author BF
 * @date 2019/5/17 14:51
 * ���Խӿ��������Ĺ�ϵ  �ӿ�
 *
 * <p> �˳���ӿ� </p>
 */
public abstract class AbstractPeople implements Animal {

    @Override
    public void cry() {
        System.out.println("�����ˣ��ҲŲ��ҽ�");
    }

    @Override
    public void fly(){
        System.out.println("�Ҳ����");
    }
    /**
     * ��
     */
    @Override
    public abstract void eat();

    /**
     * ��·
     */
    @Override
    public abstract void walk();

    /**
     * ˵������
     */
    @Override
    public abstract void speak();
}
