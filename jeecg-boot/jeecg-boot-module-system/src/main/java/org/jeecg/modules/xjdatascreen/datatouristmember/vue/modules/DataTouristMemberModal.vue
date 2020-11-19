<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="注册时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择注册时间" v-decorator="[ 'registeredTime', validatorRules.registeredTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="最后上线时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择最后上线时间" v-decorator="[ 'lastOnlineTime', validatorRules.lastOnlineTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="昵称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'nickName', validatorRules.nickName]" placeholder="请输入昵称"></a-input>
        </a-form-item>
        <a-form-item label="身份证号码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'cardId', validatorRules.cardId]" placeholder="请输入身份证号码"></a-input>
        </a-form-item>
        <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'sex', validatorRules.sex]" placeholder="请输入性别"></a-input>
        </a-form-item>
        <a-form-item label="微信唯一标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'uniqueId', validatorRules.uniqueId]" placeholder="请输入微信唯一标识"></a-input>
        </a-form-item>
        <a-form-item label="注册来源 W-微信 Z-支付宝 H-H5" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'registeredSource', validatorRules.registeredSource]" placeholder="请输入注册来源 W-微信 Z-支付宝 H-H5"></a-input>
        </a-form-item>
        <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'phoneNumber', validatorRules.phoneNumber]" placeholder="请输入手机号"></a-input>
        </a-form-item>
        <a-form-item label="供应商code" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'supplierCode', validatorRules.supplierCode]" placeholder="请输入供应商code"></a-input>
        </a-form-item>
        <a-form-item label="会员级别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'memberLevel', validatorRules.memberLevel]" placeholder="请输入会员级别"></a-input>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'status', validatorRules.status]" placeholder="请输入状态" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="微信图像" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'avatar', validatorRules.avatar]" placeholder="请输入微信图像"></a-input>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="更新时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择更新时间" v-decorator="[ 'updateTime', validatorRules.updateTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: "DataTouristMemberModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          registeredTime: {rules: [
          ]},
          lastOnlineTime: {rules: [
          ]},
          nickName: {rules: [
          ]},
          cardId: {rules: [
          ]},
          sex: {rules: [
          ]},
          uniqueId: {rules: [
          ]},
          registeredSource: {rules: [
          ]},
          phoneNumber: {rules: [
          ]},
          supplierCode: {rules: [
          ]},
          memberLevel: {rules: [
          ]},
          status: {rules: [
          ]},
          avatar: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/datatouristmember/dataTouristMember/add",
          edit: "/datatouristmember/dataTouristMember/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'registeredTime','lastOnlineTime','nickName','cardId','sex','uniqueId','registeredSource','phoneNumber','supplierCode','memberLevel','status','avatar','createBy','createTime','updateTime'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'registeredTime','lastOnlineTime','nickName','cardId','sex','uniqueId','registeredSource','phoneNumber','supplierCode','memberLevel','status','avatar','createBy','createTime','updateTime'))
      },

      
    }
  }
</script>