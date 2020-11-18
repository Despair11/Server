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

        <a-form-item label="订单id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderId', validatorRules.orderId]" placeholder="请输入订单id"></a-input>
        </a-form-item>
        <a-form-item label="订单渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderChannel', validatorRules.orderChannel]" placeholder="请输入订单渠道"></a-input>
        </a-form-item>
        <a-form-item label="产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'productName', validatorRules.productName]" placeholder="请输入产品名称"></a-input>
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'buyNumber', validatorRules.buyNumber]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'contactPerson', validatorRules.contactPerson]" placeholder="请输入联系人"></a-input>
        </a-form-item>
        <a-form-item label="联系方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mobile', validatorRules.mobile]" placeholder="请输入联系方式"></a-input>
        </a-form-item>
        <a-form-item label="使用时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择使用时间" v-decorator="[ 'useTime', validatorRules.useTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'orderPrice', validatorRules.orderPrice]" placeholder="请输入订单金额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="支付方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payType', validatorRules.payType]" placeholder="请输入支付方式"></a-input>
        </a-form-item>
        <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderStatus', validatorRules.orderStatus]" placeholder="请输入订单状态"></a-input>
        </a-form-item>
        <a-form-item label="支付单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payOrderId', validatorRules.payOrderId]" placeholder="请输入支付单号"></a-input>
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
    name: "DataWineShopModal",
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
            {required: true, message: '请输入订单id!'},
          ]},
          orderChannel: {rules: [
          ]},
          productName: {rules: [
          ]},
          buyNumber: {rules: [
          ]},
          contactPerson: {rules: [
          ]},
          mobile: {rules: [
          ]},
          useTime: {rules: [
          ]},
          orderPrice: {rules: [
          ]},
          payType: {rules: [
          ]},
          orderStatus: {rules: [
          ]},
          payOrderId: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/datawineshop/dataWineShop/add",
          edit: "/datawineshop/dataWineShop/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderId','orderChannel','productName','buyNumber','contactPerson','mobile','useTime','orderPrice','payType','orderStatus','payOrderId','createBy','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'orderId','orderChannel','productName','buyNumber','contactPerson','mobile','useTime','orderPrice','payType','orderStatus','payOrderId','createBy','createTime','updateTime'))
      },

      
    }
  }
</script>