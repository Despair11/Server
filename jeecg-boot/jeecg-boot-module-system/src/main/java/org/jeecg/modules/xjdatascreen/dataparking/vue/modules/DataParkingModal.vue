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

        <a-form-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'vplNumber', validatorRules.vplNumber]" placeholder="请输入车牌号"></a-input>
        </a-form-item>
        <a-form-item label="进车时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择进车时间" v-decorator="[ 'inTime', validatorRules.inTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="出车时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择出车时间" v-decorator="[ 'outTime', validatorRules.outTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="本次支付金额,单位分" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fee', validatorRules.fee]" placeholder="请输入本次支付金额,单位分" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="车牌颜色 0：蓝色，1：黄色，2：白色，3：黑色，4：绿色" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'vplColor', validatorRules.vplColor]" placeholder="请输入车牌颜色 0：蓝色，1：黄色，2：白色，3：黑色，4：绿色" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="停留时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'stayTime', validatorRules.stayTime]" placeholder="请输入停留时间" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="省份" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'proviceCode', validatorRules.proviceCode]" placeholder="请输入省份"></a-input>
        </a-form-item>
        <a-form-item label="入口编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'armCode', validatorRules.armCode]" placeholder="请输入入口编号"></a-input>
        </a-form-item>
        <a-form-item label="车场名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'parkName', validatorRules.parkName]" placeholder="请输入车场名称"></a-input>
        </a-form-item>
        <a-form-item label="停车记录id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'recordId', validatorRules.recordId]" placeholder="请输入停车记录id"></a-input>
        </a-form-item>
        <a-form-item label="支付时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择支付时间" v-decorator="[ 'payTime', validatorRules.payTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="支付方式 1微信  2支付宝" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'payWay', validatorRules.payWay]" placeholder="请输入支付方式 1微信  2支付宝" style="width: 100%"/>
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
    name: "DataParkingModal",
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
          vplNumber: {rules: [
          ]},
          inTime: {rules: [
          ]},
          outTime: {rules: [
          ]},
          fee: {rules: [
          ]},
          vplColor: {rules: [
          ]},
          stayTime: {rules: [
          ]},
          proviceCode: {rules: [
          ]},
          armCode: {rules: [
          ]},
          parkName: {rules: [
          ]},
          recordId: {rules: [
          ]},
          payTime: {rules: [
          ]},
          payWay: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/dataparking/dataParking/add",
          edit: "/dataparking/dataParking/edit",
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
          this.form.setFieldsValue(pick(this.model,'vplNumber','inTime','outTime','fee','vplColor','stayTime','proviceCode','armCode','parkName','recordId','payTime','payWay','createBy','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'vplNumber','inTime','outTime','fee','vplColor','stayTime','proviceCode','armCode','parkName','recordId','payTime','payWay','createBy','createTime','updateTime'))
      },

      
    }
  }
</script>