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

        <a-form-item label="订单详情编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderDetailId', validatorRules.orderDetailId]" placeholder="请输入订单详情编号"></a-input>
        </a-form-item>
        <a-form-item label="票号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'ticketNo', validatorRules.ticketNo]" placeholder="请输入票号"></a-input>
        </a-form-item>
        <a-form-item label="名字" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入名字"></a-input>
        </a-form-item>
        <a-form-item label="电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mobile', validatorRules.mobile]" placeholder="请输入电话"></a-input>
        </a-form-item>
        <a-form-item label="证件类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'cardType', validatorRules.cardType]" placeholder="请输入证件类型" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="证件编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'cardNo', validatorRules.cardNo]" placeholder="请输入证件编号"></a-input>
        </a-form-item>
        <a-form-item label="状态；1待使用 2已核验 3.已退" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'status', validatorRules.status]" placeholder="请输入状态；1待使用 2已核验 3.已退" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="票号生成的二维码地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'codeUrl', validatorRules.codeUrl]" placeholder="请输入票号生成的二维码地址"></a-input>
        </a-form-item>
        <a-form-item label="非实名制时，用他来区别出行人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'replaceCardNo', validatorRules.replaceCardNo]" placeholder="请输入非实名制时，用他来区别出行人"></a-input>
        </a-form-item>
        <a-form-item label="二次核验状态 0-未二次核验 1-已二次核验" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'doubleCheckStatus', validatorRules.doubleCheckStatus]" placeholder="请输入二次核验状态 0-未二次核验 1-已二次核验" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一次核验时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'checkTime', validatorRules.checkTime]" placeholder="请输入一次核验时间"></a-input>
        </a-form-item>
        <a-form-item label="取消/退款时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'cancelTime', validatorRules.cancelTime]" placeholder="请输入取消/退款时间"></a-input>
        </a-form-item>
        <a-form-item label="二次核验时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'doubleCheckTime', validatorRules.doubleCheckTime]" placeholder="请输入二次核验时间"></a-input>
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
    name: "TickPassengersModal",
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
          orderDetailId: {rules: [
          ]},
          ticketNo: {rules: [
          ]},
          name: {rules: [
          ]},
          mobile: {rules: [
          ]},
          cardType: {rules: [
          ]},
          cardNo: {rules: [
          ]},
          status: {rules: [
          ]},
          codeUrl: {rules: [
          ]},
          replaceCardNo: {rules: [
          ]},
          doubleCheckStatus: {rules: [
          ]},
          checkTime: {rules: [
          ]},
          cancelTime: {rules: [
          ]},
          doubleCheckTime: {rules: [
          ]},
        },
        url: {
          add: "/datatickpassengers/tickPassengers/add",
          edit: "/datatickpassengers/tickPassengers/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderDetailId','ticketNo','name','mobile','cardType','cardNo','status','codeUrl','replaceCardNo','doubleCheckStatus','checkTime','cancelTime','doubleCheckTime'))
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
        this.form.setFieldsValue(pick(row,'orderDetailId','ticketNo','name','mobile','cardType','cardNo','status','codeUrl','replaceCardNo','doubleCheckStatus','checkTime','cancelTime','doubleCheckTime'))
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