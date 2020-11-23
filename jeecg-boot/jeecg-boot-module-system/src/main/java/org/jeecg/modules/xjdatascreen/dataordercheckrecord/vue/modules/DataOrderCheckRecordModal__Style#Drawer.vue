<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderNum', validatorRules.orderNum]" placeholder="请输入订单编号"></a-input>
        </a-form-item>
        <a-form-item label="账号登录名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'loginName', validatorRules.loginName]" placeholder="请输入账号登录名称"></a-input>
        </a-form-item>
        <a-form-item label="账号名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'accountName', validatorRules.accountName]" placeholder="请输入账号名称"></a-input>
        </a-form-item>
        <a-form-item label="核验时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'checkTime', validatorRules.checkTime]" placeholder="请输入核验时间"></a-input>
        </a-form-item>
        <a-form-item label="设备描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'deviceDescription', validatorRules.deviceDescription]" placeholder="请输入设备描述"></a-input>
        </a-form-item>
        <a-form-item label="核验数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'count', validatorRules.count]" placeholder="请输入核验数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'checkStyle', validatorRules.checkStyle]" placeholder="请输入核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="核验计算方式：1-一次核验 2-二次核验" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'checkCalculateMethod', validatorRules.checkCalculateMethod]" placeholder="请输入核验计算方式：1-一次核验 2-二次核验" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="本次核验票的金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'checkAmount', validatorRules.checkAmount]" placeholder="请输入本次核验票的金额"></a-input>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  
  export default {
    name: "DataOrderCheckRecordModal",
    components: { 
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
          orderNum: {rules: [
          ]},
          loginName: {rules: [
          ]},
          accountName: {rules: [
          ]},
          checkTime: {rules: [
          ]},
          deviceDescription: {rules: [
          ]},
          count: {rules: [
          ]},
          checkStyle: {rules: [
          ]},
          checkCalculateMethod: {rules: [
          ]},
          checkAmount: {rules: [
          ]},
        },
        url: {
          add: "/dataordercheckrecord/dataOrderCheckRecord/add",
          edit: "/dataordercheckrecord/dataOrderCheckRecord/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderNum','loginName','accountName','checkTime','deviceDescription','count','checkStyle','checkCalculateMethod','checkAmount'))
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
        this.form.setFieldsValue(pick(row,'orderNum','loginName','accountName','checkTime','deviceDescription','count','checkStyle','checkCalculateMethod','checkAmount'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>