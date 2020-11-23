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
      <a-button type="primary" icon="download" @click="handleExportXls('data_order_check_record')">导出</a-button>
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

    <dataOrderCheckRecord-modal ref="modalForm" @ok="modalFormOk"></dataOrderCheckRecord-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DataOrderCheckRecordModal from './modules/DataOrderCheckRecordModal'

  export default {
    name: "DataOrderCheckRecordList",
    mixins:[JeecgListMixin],
    components: {
      DataOrderCheckRecordModal
    },
    data () {
      return {
        description: 'data_order_check_record管理页面',
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
            title:'订单编号',
            align:"center",
            dataIndex: 'orderNum'
          },
          {
            title:'账号登录名称',
            align:"center",
            dataIndex: 'loginName'
          },
          {
            title:'账号名称',
            align:"center",
            dataIndex: 'accountName'
          },
          {
            title:'核验时间',
            align:"center",
            dataIndex: 'checkTime'
          },
          {
            title:'设备描述',
            align:"center",
            dataIndex: 'deviceDescription'
          },
          {
            title:'核验数量',
            align:"center",
            dataIndex: 'count'
          },
          {
            title:'核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验',
            align:"center",
            dataIndex: 'checkStyle'
          },
          {
            title:'核验计算方式：1-一次核验 2-二次核验',
            align:"center",
            dataIndex: 'checkCalculateMethod'
          },
          {
            title:'本次核验票的金额',
            align:"center",
            dataIndex: 'checkAmount'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/dataordercheckrecord/dataOrderCheckRecord/list",
          delete: "/dataordercheckrecord/dataOrderCheckRecord/delete",
          deleteBatch: "/dataordercheckrecord/dataOrderCheckRecord/deleteBatch",
          exportXlsUrl: "/dataordercheckrecord/dataOrderCheckRecord/exportXls",
          importExcelUrl: "dataordercheckrecord/dataOrderCheckRecord/importExcel",
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