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
        <a-form-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderNum', validatorRules.orderNum]" placeholder="请输入订单编号"></a-input>
        </a-form-item>
        <a-form-item label="票的id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'ticketId', validatorRules.ticketId]" placeholder="请输入票的id" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="是否一人多票" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'isMore', validatorRules.isMore]" placeholder="请输入是否一人多票" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="是否实名制" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'isRealName', validatorRules.isRealName]" placeholder="请输入是否实名制" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="票的产品编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'ticketCode', validatorRules.ticketCode]" placeholder="请输入票的产品编号"></a-input>
        </a-form-item>
        <a-form-item label="单价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'unitPrice', validatorRules.unitPrice]" placeholder="请输入单价"></a-input>
        </a-form-item>
        <a-form-item label="总价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'totalPrice', validatorRules.totalPrice]" placeholder="请输入总价"></a-input>
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'quantity', validatorRules.quantity]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="票种名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'typeName', validatorRules.typeName]" placeholder="请输入票种名称"></a-input>
        </a-form-item>
        <a-form-item label="票的名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'ticketName', validatorRules.ticketName]" placeholder="请输入票的名称"></a-input>
        </a-form-item>
        <a-form-item label="房型名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'roomName', validatorRules.roomName]" placeholder="请输入房型名称"></a-input>
        </a-form-item>
        <a-form-item label="房型id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'hotelRoomId', validatorRules.hotelRoomId]" placeholder="请输入房型id" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="房型编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hotelRoomCode', validatorRules.hotelRoomCode]" placeholder="请输入房型编号"></a-input>
        </a-form-item>
        <a-form-item label="使用日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'useDate', validatorRules.useDate]" placeholder="请输入使用日期"></a-input>
        </a-form-item>
        <a-form-item label="票的日库存id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'timeDivisionStockId', validatorRules.timeDivisionStockId]" placeholder="请输入票的日库存id" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="使用数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'useCount', validatorRules.useCount]" placeholder="请输入使用数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="退的数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'cancelCount', validatorRules.cancelCount]" placeholder="请输入退的数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="使用有效截止日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'endDate', validatorRules.endDate]" placeholder="请输入使用有效截止日期"></a-input>
        </a-form-item>
        <a-form-item label="票的上一级编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'superTicketCode', validatorRules.superTicketCode]" placeholder="请输入票的上一级编号"></a-input>
        </a-form-item>
        <a-form-item label="分时库存时段开始时间 HH:mm" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'startTime', validatorRules.startTime]" placeholder="请输入分时库存时段开始时间 HH:mm"></a-input>
        </a-form-item>
        <a-form-item label="分时库存时段结束时间 HH:mm" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'endTime', validatorRules.endTime]" placeholder="请输入分时库存时段结束时间 HH:mm"></a-input>
        </a-form-item>
        <a-form-item label="二次核验的数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'doubleCheckCount', validatorRules.doubleCheckCount]" placeholder="请输入二次核验的数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="已退款金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'refundAmount', validatorRules.refundAmount]" placeholder="请输入已退款金额"></a-input>
        </a-form-item>
        <a-form-item label="OTA售卖给用户的价格" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'otaBuyPrice', validatorRules.otaBuyPrice]" placeholder="请输入OTA售卖给用户的价格"></a-input>
        </a-form-item>
        <a-form-item label="OTA产品编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'otaProductCode', validatorRules.otaProductCode]" placeholder="请输入OTA产品编号"></a-input>
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
    name: "TicketOrderDetailModal",
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
          orderNum: {rules: [
          ]},
          ticketId: {rules: [
          ]},
          isMore: {rules: [
          ]},
          isRealName: {rules: [
          ]},
          ticketCode: {rules: [
          ]},
          unitPrice: {rules: [
          ]},
          totalPrice: {rules: [
          ]},
          quantity: {rules: [
          ]},
          typeName: {rules: [
          ]},
          ticketName: {rules: [
          ]},
          roomName: {rules: [
          ]},
          hotelRoomId: {rules: [
          ]},
          hotelRoomCode: {rules: [
          ]},
          useDate: {rules: [
          ]},
          timeDivisionStockId: {rules: [
          ]},
          useCount: {rules: [
          ]},
          cancelCount: {rules: [
          ]},
          endDate: {rules: [
          ]},
          superTicketCode: {rules: [
          ]},
          startTime: {rules: [
          ]},
          endTime: {rules: [
          ]},
          doubleCheckCount: {rules: [
          ]},
          refundAmount: {rules: [
          ]},
          otaBuyPrice: {rules: [
          ]},
          otaProductCode: {rules: [
          ]},
        },
        url: {
          add: "/dataticketorderdetail/ticketOrderDetail/add",
          edit: "/dataticketorderdetail/ticketOrderDetail/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderDetailId','orderNum','ticketId','isMore','isRealName','ticketCode','unitPrice','totalPrice','quantity','typeName','ticketName','roomName','hotelRoomId','hotelRoomCode','useDate','timeDivisionStockId','useCount','cancelCount','endDate','superTicketCode','startTime','endTime','doubleCheckCount','refundAmount','otaBuyPrice','otaProductCode'))
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
        this.form.setFieldsValue(pick(row,'orderDetailId','orderNum','ticketId','isMore','isRealName','ticketCode','unitPrice','totalPrice','quantity','typeName','ticketName','roomName','hotelRoomId','hotelRoomCode','useDate','timeDivisionStockId','useCount','cancelCount','endDate','superTicketCode','startTime','endTime','doubleCheckCount','refundAmount','otaBuyPrice','otaProductCode'))
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