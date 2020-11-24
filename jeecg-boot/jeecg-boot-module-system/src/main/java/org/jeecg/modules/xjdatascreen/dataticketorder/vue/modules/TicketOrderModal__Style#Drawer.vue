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

        <a-form-item label="景区编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'scenicNum', validatorRules.scenicNum]" placeholder="请输入景区编号"></a-input>
        </a-form-item>
        <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderAmount', validatorRules.orderAmount]" placeholder="请输入订单金额"></a-input>
        </a-form-item>
        <a-form-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderNum', validatorRules.orderNum]" placeholder="请输入订单编号"></a-input>
        </a-form-item>
        <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'status', validatorRules.status]" placeholder="请输入订单状态"></a-input>
        </a-form-item>
        <a-form-item label="支付状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payStatus', validatorRules.payStatus]" placeholder="请输入支付状态"></a-input>
        </a-form-item>
        <a-form-item label="下单时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createTime', validatorRules.createTime]" placeholder="请输入下单时间"></a-input>
        </a-form-item>
        <a-form-item label="销售渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderChannel', validatorRules.orderChannel]" placeholder="请输入销售渠道"></a-input>
        </a-form-item>
        <a-form-item label="支付方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'paymentMode', validatorRules.paymentMode]" placeholder="请输入支付方式"></a-input>
        </a-form-item>
        <a-form-item label="使用时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'useTime', validatorRules.useTime]" placeholder="请输入使用时间"></a-input>
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
        <a-form-item label="联系人名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactName', validatorRules.contactName]" placeholder="请输入联系人名称"></a-input>
        </a-form-item>
        <a-form-item label="联系人电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactMobile', validatorRules.contactMobile]" placeholder="请输入联系人电话"></a-input>
        </a-form-item>
        <a-form-item label="联系人证件类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'contactCardType', validatorRules.contactCardType]" placeholder="请输入联系人证件类型" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="联系人证件号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactCardNo', validatorRules.contactCardNo]" placeholder="请输入联系人证件号"></a-input>
        </a-form-item>
        <a-form-item label="取票号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'pickupTicketNo', validatorRules.pickupTicketNo]" placeholder="请输入取票号"></a-input>
        </a-form-item>
        <a-form-item label="用户唯一标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'owner', validatorRules.owner]" placeholder="请输入用户唯一标识"></a-input>
        </a-form-item>
        <a-form-item label="支付时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payTime', validatorRules.payTime]" placeholder="请输入支付时间"></a-input>
        </a-form-item>
        <a-form-item label="订单类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderType', validatorRules.orderType]" placeholder="请输入订单类型" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="startDate" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'startDate', validatorRules.startDate]" placeholder="请输入startDate"></a-input>
        </a-form-item>
        <a-form-item label="endDate" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'endDate', validatorRules.endDate]" placeholder="请输入endDate"></a-input>
        </a-form-item>
        <a-form-item label="酒店编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hotelCode', validatorRules.hotelCode]" placeholder="请输入酒店编号"></a-input>
        </a-form-item>
        <a-form-item label="支付单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payOrderId', validatorRules.payOrderId]" placeholder="请输入支付单号"></a-input>
        </a-form-item>
        <a-form-item label="产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'productName', validatorRules.productName]" placeholder="请输入产品名称"></a-input>
        </a-form-item>
        <a-form-item label="子产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'subProductName', validatorRules.subProductName]" placeholder="请输入子产品名称"></a-input>
        </a-form-item>
        <a-form-item label="订单数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderCount', validatorRules.orderCount]" placeholder="请输入订单数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="售后状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'afterSaleStatus', validatorRules.afterSaleStatus]" placeholder="请输入售后状态"></a-input>
        </a-form-item>
        <a-form-item label="套票产品id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'packageTicketId', validatorRules.packageTicketId]" placeholder="请输入套票产品id" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="是否删除，0-未删除 1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'isDelete', validatorRules.isDelete]" placeholder="请输入是否删除，0-未删除 1-已删除" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="取票号生成的二维码地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'codeUrl', validatorRules.codeUrl]" placeholder="请输入取票号生成的二维码地址"></a-input>
        </a-form-item>
        <a-form-item label="对应套票的父订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'pid', validatorRules.pid]" placeholder="请输入对应套票的父订单号"></a-input>
        </a-form-item>
        <a-form-item label="检票方式，1-一码多刷 2-一码一刷" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'checkStyle', validatorRules.checkStyle]" placeholder="请输入检票方式，1-一码多刷 2-一码一刷" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="核验方式：1-一次核验 2-二次核验" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'verificationMethod', validatorRules.verificationMethod]" placeholder="请输入核验方式：1-一次核验 2-二次核验" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="票务系统类型：0-自己平台 1-九天达" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'ticketSystemType', validatorRules.ticketSystemType]" placeholder="请输入票务系统类型：0-自己平台 1-九天达" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="对应第三方系统订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'thirdOrderNum', validatorRules.thirdOrderNum]" placeholder="请输入对应第三方系统订单编号"></a-input>
        </a-form-item>
        <a-form-item label="检票完结时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'checkFinishTime', validatorRules.checkFinishTime]" placeholder="请输入检票完结时间"></a-input>
        </a-form-item>
        <a-form-item label="商家编号（订单中商品是哪个商家的" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'merchantCode', validatorRules.merchantCode]" placeholder="请输入商家编号（订单中商品是哪个商家的"></a-input>
        </a-form-item>
        <a-form-item label="OTA类型 1-美团" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'otaType', validatorRules.otaType]" placeholder="请输入OTA类型 1-美团" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="OTA订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'otaOrderNum', validatorRules.otaOrderNum]" placeholder="请输入OTA订单编号"></a-input>
        </a-form-item>
        <a-form-item label="对应ota的合作方id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'otaPartnerId', validatorRules.otaPartnerId]" placeholder="请输入对应ota的合作方id"></a-input>
        </a-form-item>
        <a-form-item label="ota传过来的订单金额，用户付给ota的钱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'otaOrderAmount', validatorRules.otaOrderAmount]" placeholder="请输入ota传过来的订单金额，用户付给ota的钱"></a-input>
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
    name: "TicketOrderModal",
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
          scenicNum: {rules: [
          ]},
          orderAmount: {rules: [
          ]},
          orderNum: {rules: [
          ]},
          status: {rules: [
          ]},
          payStatus: {rules: [
          ]},
          createTime: {rules: [
          ]},
          orderChannel: {rules: [
          ]},
          paymentMode: {rules: [
          ]},
          useTime: {rules: [
          ]},
          orderCustomerName: {rules: [
          ]},
          orderCustomerType: {rules: [
          ]},
          orderCustomerNo: {rules: [
          ]},
          contactName: {rules: [
          ]},
          contactMobile: {rules: [
          ]},
          contactCardType: {rules: [
          ]},
          contactCardNo: {rules: [
          ]},
          pickupTicketNo: {rules: [
          ]},
          owner: {rules: [
          ]},
          payTime: {rules: [
          ]},
          orderType: {rules: [
          ]},
          startDate: {rules: [
          ]},
          endDate: {rules: [
          ]},
          hotelCode: {rules: [
          ]},
          payOrderId: {rules: [
          ]},
          productName: {rules: [
          ]},
          subProductName: {rules: [
          ]},
          orderCount: {rules: [
          ]},
          afterSaleStatus: {rules: [
          ]},
          packageTicketId: {rules: [
          ]},
          isDelete: {rules: [
          ]},
          codeUrl: {rules: [
          ]},
          pid: {rules: [
          ]},
          checkStyle: {rules: [
          ]},
          verificationMethod: {rules: [
          ]},
          ticketSystemType: {rules: [
          ]},
          thirdOrderNum: {rules: [
          ]},
          checkFinishTime: {rules: [
          ]},
          merchantCode: {rules: [
          ]},
          otaType: {rules: [
          ]},
          otaOrderNum: {rules: [
          ]},
          otaPartnerId: {rules: [
          ]},
          otaOrderAmount: {rules: [
          ]},
        },
        url: {
          add: "/dataticketorder/ticketOrder/add",
          edit: "/dataticketorder/ticketOrder/edit",
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
          this.form.setFieldsValue(pick(this.model,'scenicNum','orderAmount','orderNum','status','payStatus','createTime','orderChannel','paymentMode','useTime','orderCustomerName','orderCustomerType','orderCustomerNo','contactName','contactMobile','contactCardType','contactCardNo','pickupTicketNo','owner','payTime','orderType','startDate','endDate','hotelCode','payOrderId','productName','subProductName','orderCount','afterSaleStatus','packageTicketId','isDelete','codeUrl','pid','checkStyle','verificationMethod','ticketSystemType','thirdOrderNum','checkFinishTime','merchantCode','otaType','otaOrderNum','otaPartnerId','otaOrderAmount'))
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
        this.form.setFieldsValue(pick(row,'scenicNum','orderAmount','orderNum','status','payStatus','createTime','orderChannel','paymentMode','useTime','orderCustomerName','orderCustomerType','orderCustomerNo','contactName','contactMobile','contactCardType','contactCardNo','pickupTicketNo','owner','payTime','orderType','startDate','endDate','hotelCode','payOrderId','productName','subProductName','orderCount','afterSaleStatus','packageTicketId','isDelete','codeUrl','pid','checkStyle','verificationMethod','ticketSystemType','thirdOrderNum','checkFinishTime','merchantCode','otaType','otaOrderNum','otaPartnerId','otaOrderAmount'))
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