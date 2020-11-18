<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('data_gcxx')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <dataGcxx-modal ref="modalForm" @ok="modalFormOk"></dataGcxx-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DataGcxxModal from './modules/DataGcxxModal'

  export default {
    name: "DataGcxxList",
    mixins:[JeecgListMixin],
    components: {
      DataGcxxModal
    },
    data () {
      return {
        description: 'data_gcxx管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'序号',
            align:"center",
            dataIndex: 'clxxbh'
          },
          {
            title:'卡口编号',
            align:"center",
            dataIndex: 'kkbh'
          },
          {
            title:'卡口名称',
            align:"center",
            dataIndex: 'kkmc'
          },
          {
            title:'维度',
            align:"center",
            dataIndex: 'wd'
          },
          {
            title:'经度',
            align:"center",
            dataIndex: 'jd'
          },
          {
            title:'车道编号',
            align:"center",
            dataIndex: 'cdbh'
          },
          {
            title:'号牌号码',
            align:"center",
            dataIndex: 'hphm'
          },
          {
            title:'号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他',
            align:"center",
            dataIndex: 'hpys'
          },
          {
            title:'行驶方向代码',
            align:"center",
            dataIndex: 'xsfxdm'
          },
          {
            title:'行驶方向',
            align:"center",
            dataIndex: 'xsfx'
          },
          {
            title:'车辆类型
0-未识别
1-大客车
2-货车
3-轿车
4-面包车
5-小货车
6-行人
7-二轮车
9-SUV/MVP
10-中型客车',
            align:"center",
            dataIndex: 'cllx'
          },
          {
            title:'车身颜色',
            align:"center",
            dataIndex: 'csys'
          },
          {
            title:'号牌种类
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
17-新能源车',
            align:"center",
            dataIndex: 'hpzl'
          },
          {
            title:'车辆速度',
            align:"center",
            dataIndex: 'clsd'
          },
          {
            title:'车牌图片',
            align:"center",
            dataIndex: 'hptp'
          },
          {
            title:'过车图片',
            align:"center",
            dataIndex: 'qjtp'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'更新时间',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'updateTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/datagcxx/dataGcxx/list",
          delete: "/datagcxx/dataGcxx/delete",
          deleteBatch: "/datagcxx/dataGcxx/deleteBatch",
          exportXlsUrl: "/datagcxx/dataGcxx/exportXls",
          importExcelUrl: "datagcxx/dataGcxx/importExcel",
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>