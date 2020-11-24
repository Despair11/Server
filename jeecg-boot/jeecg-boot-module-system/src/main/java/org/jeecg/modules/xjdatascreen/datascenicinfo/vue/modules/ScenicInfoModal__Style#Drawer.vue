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

        <a-form-item label="scenicCode" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'scenicCode', validatorRules.scenicCode]" placeholder="请输入scenicCode"></a-input>
        </a-form-item>
        <a-form-item label="scenicName" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'scenicName', validatorRules.scenicName]" placeholder="请输入scenicName"></a-input>
        </a-form-item>
        <a-form-item label="scenicCharacteristics" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'scenicCharacteristics', validatorRules.scenicCharacteristics]" placeholder="请输入scenicCharacteristics"></a-input>
        </a-form-item>
        <a-form-item label="addressLongitude" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'addressLongitude', validatorRules.addressLongitude]" placeholder="请输入addressLongitude"></a-input>
        </a-form-item>
        <a-form-item label="addressLatitude" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'addressLatitude', validatorRules.addressLatitude]" placeholder="请输入addressLatitude"></a-input>
        </a-form-item>
        <a-form-item label="telephone" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'telephone', validatorRules.telephone]" placeholder="请输入telephone"></a-input>
        </a-form-item>
        <a-form-item label="coverVideoUrl" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'coverVideoUrl', validatorRules.coverVideoUrl]" placeholder="请输入coverVideoUrl"></a-input>
        </a-form-item>
        <a-form-item label="coverImageUrl" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'coverImageUrl', validatorRules.coverImageUrl]" placeholder="请输入coverImageUrl"></a-input>
        </a-form-item>
        <a-form-item label="summarize" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['summarize', validatorRules.summarize]" rows="4" placeholder="请输入summarize"/>
        </a-form-item>
        <a-form-item label="trafficGuide" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['trafficGuide', validatorRules.trafficGuide]" rows="4" placeholder="请输入trafficGuide"/>
        </a-form-item>
        <a-form-item label="ambitusStay" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['ambitusStay', validatorRules.ambitusStay]" rows="4" placeholder="请输入ambitusStay"/>
        </a-form-item>
        <a-form-item label="featuredItem" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['featuredItem', validatorRules.featuredItem]" rows="4" placeholder="请输入featuredItem"/>
        </a-form-item>
        <a-form-item label="recommend" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'recommend', validatorRules.recommend]" placeholder="请输入recommend"></a-input>
        </a-form-item>
        <a-form-item label="status" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'status', validatorRules.status]" placeholder="请输入status"></a-input>
        </a-form-item>
        <a-form-item label="sortNo" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'sortNo', validatorRules.sortNo]" placeholder="请输入sortNo" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="createTime" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createTime', validatorRules.createTime]" placeholder="请输入createTime"></a-input>
        </a-form-item>
        <a-form-item label="supplierCode" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'supplierCode', validatorRules.supplierCode]" placeholder="请输入supplierCode"></a-input>
        </a-form-item>
        <a-form-item label="supplierName" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'supplierName', validatorRules.supplierName]" placeholder="请输入supplierName"></a-input>
        </a-form-item>
        <a-form-item label="state" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'state', validatorRules.state]" placeholder="请输入state"></a-input>
        </a-form-item>
        <a-form-item label="verifyTime" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'verifyTime', validatorRules.verifyTime]" placeholder="请输入verifyTime"></a-input>
        </a-form-item>
        <a-form-item label="verifyRefuseReason" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'verifyRefuseReason', validatorRules.verifyRefuseReason]" placeholder="请输入verifyRefuseReason"></a-input>
        </a-form-item>
        <a-form-item label="address" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'address', validatorRules.address]" placeholder="请输入address"></a-input>
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
    name: "ScenicInfoModal",
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
          scenicCode: {rules: [
          ]},
          scenicName: {rules: [
          ]},
          scenicCharacteristics: {rules: [
          ]},
          addressLongitude: {rules: [
          ]},
          addressLatitude: {rules: [
          ]},
          telephone: {rules: [
          ]},
          coverVideoUrl: {rules: [
          ]},
          coverImageUrl: {rules: [
          ]},
          summarize: {rules: [
          ]},
          trafficGuide: {rules: [
          ]},
          ambitusStay: {rules: [
          ]},
          featuredItem: {rules: [
          ]},
          recommend: {rules: [
          ]},
          status: {rules: [
          ]},
          sortNo: {rules: [
          ]},
          createTime: {rules: [
          ]},
          supplierCode: {rules: [
          ]},
          supplierName: {rules: [
          ]},
          state: {rules: [
          ]},
          verifyTime: {rules: [
          ]},
          verifyRefuseReason: {rules: [
          ]},
          address: {rules: [
          ]},
        },
        url: {
          add: "/datascenicinfo/scenicInfo/add",
          edit: "/datascenicinfo/scenicInfo/edit",
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
          this.form.setFieldsValue(pick(this.model,'scenicCode','scenicName','scenicCharacteristics','addressLongitude','addressLatitude','telephone','coverVideoUrl','coverImageUrl','summarize','trafficGuide','ambitusStay','featuredItem','recommend','status','sortNo','createTime','supplierCode','supplierName','state','verifyTime','verifyRefuseReason','address'))
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
        this.form.setFieldsValue(pick(row,'scenicCode','scenicName','scenicCharacteristics','addressLongitude','addressLatitude','telephone','coverVideoUrl','coverImageUrl','summarize','trafficGuide','ambitusStay','featuredItem','recommend','status','sortNo','createTime','supplierCode','supplierName','state','verifyTime','verifyRefuseReason','address'))
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