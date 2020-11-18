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

        <a-form-item label="序号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'clxxbh', validatorRules.clxxbh]" placeholder="请输入序号"></a-input>
        </a-form-item>
        <a-form-item label="卡口编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kkbh', validatorRules.kkbh]" placeholder="请输入卡口编号"></a-input>
        </a-form-item>
        <a-form-item label="卡口名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kkmc', validatorRules.kkmc]" placeholder="请输入卡口名称"></a-input>
        </a-form-item>
        <a-form-item label="维度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'wd', validatorRules.wd]" placeholder="请输入维度"></a-input>
        </a-form-item>
        <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'jd', validatorRules.jd]" placeholder="请输入经度"></a-input>
        </a-form-item>
        <a-form-item label="车道编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'cdbh', validatorRules.cdbh]" placeholder="请输入车道编号"></a-input>
        </a-form-item>
        <a-form-item label="号牌号码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hphm', validatorRules.hphm]" placeholder="请输入号牌号码"></a-input>
        </a-form-item>
        <a-form-item label="号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hpys', validatorRules.hpys]" placeholder="请输入号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他"></a-input>
        </a-form-item>
        <a-form-item label="行驶方向代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'xsfxdm', validatorRules.xsfxdm]" placeholder="请输入行驶方向代码"></a-input>
        </a-form-item>
        <a-form-item label="行驶方向" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'xsfx', validatorRules.xsfx]" placeholder="请输入行驶方向"></a-input>
        </a-form-item>
        <a-form-item label="车辆类型
0-未识别
1-大客车
2-货车
3-轿车
4-面包车
5-小货车
6-行人
7-二轮车
9-SUV/MVP
10-中型客车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'cllx', validatorRules.cllx]" placeholder="请输入车辆类型
0-未识别
1-大客车
2-货车
3-轿车
4-面包车
5-小货车
6-行人
7-二轮车
9-SUV/MVP
10-中型客车"></a-input>
        </a-form-item>
        <a-form-item label="车身颜色" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'csys', validatorRules.csys]" placeholder="请输入车身颜色"></a-input>
        </a-form-item>
        <a-form-item label="号牌种类
0-未识别
1- 92式民用车
2-警用车
5-左右军车车牌类型(一行结构)
7- 02式个性化车
8-黄色双行尾牌
10-使馆车
11-武警车
13-黄色1225农用车
16-摩托车
17-新能源车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hpzl', validatorRules.hpzl]" placeholder="请输入号牌种类
0-未识别
1- 92式民用车
2-警用车
5-左右军车车牌类型(一行结构)
7- 02式个性化车
8-黄色双行尾牌
10-使馆车
11-武警车
13-黄色1225农用车
16-摩托车
17-新能源车"></a-input>
        </a-form-item>
        <a-form-item label="车辆速度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'clsd', validatorRules.clsd]" placeholder="请输入车辆速度"></a-input>
        </a-form-item>
        <a-form-item label="车牌图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hptp', validatorRules.hptp]" placeholder="请输入车牌图片"></a-input>
        </a-form-item>
        <a-form-item label="过车图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'qjtp', validatorRules.qjtp]" placeholder="请输入过车图片"></a-input>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="更新时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择更新时间" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="[ 'updateTime', validatorRules.updateTime]" :trigger-change="true" style="width: 100%"/>
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
    name: "DataGcxxModal",
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
          clxxbh: {rules: [
            {required: true, message: '请输入序号!'},
          ]},
          kkbh: {rules: [
          ]},
          kkmc: {rules: [
          ]},
          wd: {rules: [
          ]},
          jd: {rules: [
          ]},
          cdbh: {rules: [
          ]},
          hphm: {rules: [
          ]},
          hpys: {rules: [
          ]},
          xsfxdm: {rules: [
          ]},
          xsfx: {rules: [
          ]},
          cllx: {rules: [
          ]},
          csys: {rules: [
          ]},
          hpzl: {rules: [
          ]},
          clsd: {rules: [
          ]},
          hptp: {rules: [
          ]},
          qjtp: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/datagcxx/dataGcxx/add",
          edit: "/datagcxx/dataGcxx/edit",
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
          this.form.setFieldsValue(pick(this.model,'clxxbh','kkbh','kkmc','wd','jd','cdbh','hphm','hpys','xsfxdm','xsfx','cllx','csys','hpzl','clsd','hptp','qjtp','createBy','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'clxxbh','kkbh','kkmc','wd','jd','cdbh','hphm','hpys','xsfxdm','xsfx','cllx','csys','hpzl','clsd','hptp','qjtp','createBy','createTime','updateTime'))
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