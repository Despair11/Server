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

        <a-form-item label="单票订单id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderId', validatorRules.orderId]" placeholder="请输入单票订单id"></a-input>
        </a-form-item>
        <a-form-item label="订单渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderChannel', validatorRules.orderChannel]" placeholder="请输入订单渠道"></a-input>
        </a-form-item>
        <a-form-item label="产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'productName', validatorRules.productName]" placeholder="请输入产品名称"></a-input>
        </a-form-item>
        <a-form-item label="票种名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'ticketName', validatorRules.ticketName]" placeholder="请输入票种名称"></a-input>
        </a-form-item>
        <a-form-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactPerson', validatorRules.contactPerson]" placeholder="请输入联系人"></a-input>
        </a-form-item>
        <a-form-item label="联系电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mobile', validatorRules.mobile]" placeholder="请输入联系电话"></a-input>
        </a-form-item>
        <a-form-item label="使用日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择使用日期" v-decorator="[ 'useTime', validatorRules.useTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderPrice', validatorRules.orderPrice]" placeholder="请输入订单金额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="购买数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'buyNumber', validatorRules.buyNumber]" placeholder="请输入购买数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="支付方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payType', validatorRules.payType]" placeholder="请输入支付方式"></a-input>
        </a-form-item>
        <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderStatus', validatorRules.orderStatus]" placeholder="请输入订单状态"></a-input>
        </a-form-item>
        <a-form-item label="支付订单" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payOrderId', validatorRules.payOrderId]" placeholder="请输入支付订单"></a-input>
        </a-form-item>
        <a-form-item label="第三方订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'thirdOrderId', validatorRules.thirdOrderId]" placeholder="请输入第三方订单号"></a-input>
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
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "DataSingleTicketModal",
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
          orderId: {rules: [
            {required: true, message: '请输入单票订单id!'},
          ]},
          orderChannel: {rules: [
          ]},
          productName: {rules: [
          ]},
          ticketName: {rules: [
          ]},
          contactPerson: {rules: [
          ]},
          mobile: {rules: [
          ]},
          useTime: {rules: [
          ]},
          orderPrice: {rules: [
          ]},
          buyNumber: {rules: [
          ]},
          payType: {rules: [
          ]},
          orderStatus: {rules: [
          ]},
          payOrderId: {rules: [
          ]},
          thirdOrderId: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/datasingleticket/dataSingleTicket/add",
          edit: "/datasingleticket/dataSingleTicket/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderId','orderChannel','productName','ticketName','contactPerson','mobile','useTime','orderPrice','buyNumber','payType','orderStatus','payOrderId','thirdOrderId','createBy','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'orderId','orderChannel','productName','ticketName','contactPerson','mobile','useTime','orderPrice','buyNumber','payType','orderStatus','payOrderId','thirdOrderId','createBy','createTime','updateTime'))
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