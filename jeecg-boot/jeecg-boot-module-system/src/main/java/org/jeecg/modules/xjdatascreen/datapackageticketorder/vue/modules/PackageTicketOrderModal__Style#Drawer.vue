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

        <a-form-item label="套票订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderNum', validatorRules.orderNum]" placeholder="请输入套票订单号"></a-input>
        </a-form-item>
        <a-form-item label="景区编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'scenicNum', validatorRules.scenicNum]" placeholder="请输入景区编号"></a-input>
        </a-form-item>
        <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderAmount', validatorRules.orderAmount]" placeholder="请输入订单金额"></a-input>
        </a-form-item>
        <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'status', validatorRules.status]" placeholder="请输入订单状态"></a-input>
        </a-form-item>
        <a-form-item label="订单支付状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payStatus', validatorRules.payStatus]" placeholder="请输入订单支付状态"></a-input>
        </a-form-item>
        <a-form-item label="订单创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createTime', validatorRules.createTime]" placeholder="请输入订单创建时间"></a-input>
        </a-form-item>
        <a-form-item label="订单来源" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderChannel', validatorRules.orderChannel]" placeholder="请输入订单来源"></a-input>
        </a-form-item>
        <a-form-item label="支付方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'paymentModel', validatorRules.paymentModel]" placeholder="请输入支付方式"></a-input>
        </a-form-item>
        <a-form-item label="使用日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'useDate', validatorRules.useDate]" placeholder="请输入使用日期"></a-input>
        </a-form-item>
        <a-form-item label="联系人名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactName', validatorRules.contactName]" placeholder="请输入联系人名称"></a-input>
        </a-form-item>
        <a-form-item label="联系人手机" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactMobile', validatorRules.contactMobile]" placeholder="请输入联系人手机"></a-input>
        </a-form-item>
        <a-form-item label="联系人证件类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'contactCardType', validatorRules.contactCardType]" placeholder="请输入联系人证件类型" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="联系人证件号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactCardNo', validatorRules.contactCardNo]" placeholder="请输入联系人证件号"></a-input>
        </a-form-item>
        <a-form-item label="用户唯一标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'owner', validatorRules.owner]" placeholder="请输入用户唯一标识"></a-input>
        </a-form-item>
        <a-form-item label="取票号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'pickupTicketNo', validatorRules.pickupTicketNo]" placeholder="请输入取票号"></a-input>
        </a-form-item>
        <a-form-item label="支付时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payTime', validatorRules.payTime]" placeholder="请输入支付时间"></a-input>
        </a-form-item>
        <a-form-item label="支付订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payOrderId', validatorRules.payOrderId]" placeholder="请输入支付订单号"></a-input>
        </a-form-item>
        <a-form-item label="产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'productName', validatorRules.productName]" placeholder="请输入产品名称"></a-input>
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderCount', validatorRules.orderCount]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="产品编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'productCode', validatorRules.productCode]" placeholder="请输入产品编号"></a-input>
        </a-form-item>
        <a-form-item label="用户是否删除标识 0-未删除 1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'isDelete', validatorRules.isDelete]" placeholder="请输入用户是否删除标识 0-未删除 1-已删除" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="套票单价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'unitPrice', validatorRules.unitPrice]" placeholder="请输入套票单价"></a-input>
        </a-form-item>
        <a-form-item label="订单类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderType', validatorRules.orderType]" placeholder="请输入订单类型" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="已核验数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'useCount', validatorRules.useCount]" placeholder="请输入已核验数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="已退数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'cancelCount', validatorRules.cancelCount]" placeholder="请输入已退数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="有效使用结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'endDate', validatorRules.endDate]" placeholder="请输入有效使用结束日期"></a-input>
        </a-form-item>
        <a-form-item label="分时库存开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'startTime', validatorRules.startTime]" placeholder="请输入分时库存开始时间"></a-input>
        </a-form-item>
        <a-form-item label="分时库存结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'endTime', validatorRules.endTime]" placeholder="请输入分时库存结束时间"></a-input>
        </a-form-item>
        <a-form-item label="售后订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'afterSaleStatus', validatorRules.afterSaleStatus]" placeholder="请输入售后订单状态"></a-input>
        </a-form-item>
        <a-form-item label="订单完结时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'checkFinishTime', validatorRules.checkFinishTime]" placeholder="请输入订单完结时间"></a-input>
        </a-form-item>
        <a-form-item label="商家编号（订单中商品是哪个商家的" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'merchantCode', validatorRules.merchantCode]" placeholder="请输入商家编号（订单中商品是哪个商家的"></a-input>
        </a-form-item>
        <a-form-item label="已退款金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'refundAmount', validatorRules.refundAmount]" placeholder="请输入已退款金额"></a-input>
        </a-form-item>
        <a-form-item label="客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderCustomerName', validatorRules.orderCustomerName]" placeholder="请输入客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源"></a-input>
        </a-form-item>
        <a-form-item label="客户类型;T为分销商；C为散客编号为空；" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderCustomerType', validatorRules.orderCustomerType]" placeholder="请输入客户类型;T为分销商；C为散客编号为空；"></a-input>
        </a-form-item>
        <a-form-item label="分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderCustomerNo', validatorRules.orderCustomerNo]" placeholder="请输入分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号"></a-input>
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
    name: "PackageTicketOrderModal",
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
          scenicNum: {rules: [
          ]},
          orderAmount: {rules: [
          ]},
          status: {rules: [
          ]},
          payStatus: {rules: [
          ]},
          createTime: {rules: [
          ]},
          orderChannel: {rules: [
          ]},
          paymentModel: {rules: [
          ]},
          useDate: {rules: [
          ]},
          contactName: {rules: [
          ]},
          contactMobile: {rules: [
          ]},
          contactCardType: {rules: [
          ]},
          contactCardNo: {rules: [
          ]},
          owner: {rules: [
          ]},
          pickupTicketNo: {rules: [
          ]},
          payTime: {rules: [
          ]},
          payOrderId: {rules: [
          ]},
          productName: {rules: [
          ]},
          orderCount: {rules: [
          ]},
          productCode: {rules: [
          ]},
          isDelete: {rules: [
          ]},
          unitPrice: {rules: [
          ]},
          orderType: {rules: [
          ]},
          useCount: {rules: [
          ]},
          cancelCount: {rules: [
          ]},
          endDate: {rules: [
          ]},
          startTime: {rules: [
          ]},
          endTime: {rules: [
          ]},
          afterSaleStatus: {rules: [
          ]},
          checkFinishTime: {rules: [
          ]},
          merchantCode: {rules: [
          ]},
          refundAmount: {rules: [
          ]},
          orderCustomerName: {rules: [
          ]},
          orderCustomerType: {rules: [
          ]},
          orderCustomerNo: {rules: [
          ]},
        },
        url: {
          add: "/datapackageticketorder/packageTicketOrder/add",
          edit: "/datapackageticketorder/packageTicketOrder/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderNum','scenicNum','orderAmount','status','payStatus','createTime','orderChannel','paymentModel','useDate','contactName','contactMobile','contactCardType','contactCardNo','owner','pickupTicketNo','payTime','payOrderId','productName','orderCount','productCode','isDelete','unitPrice','orderType','useCount','cancelCount','endDate','startTime','endTime','afterSaleStatus','checkFinishTime','merchantCode','refundAmount','orderCustomerName','orderCustomerType','orderCustomerNo'))
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
        this.form.setFieldsValue(pick(row,'orderNum','scenicNum','orderAmount','status','payStatus','createTime','orderChannel','paymentModel','useDate','contactName','contactMobile','contactCardType','contactCardNo','owner','pickupTicketNo','payTime','payOrderId','productName','orderCount','productCode','isDelete','unitPrice','orderType','useCount','cancelCount','endDate','startTime','endTime','afterSaleStatus','checkFinishTime','merchantCode','refundAmount','orderCustomerName','orderCustomerType','orderCustomerNo'))
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