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
      <a-button type="primary" icon="download" @click="handleExportXls('tick_passengers')">导出</a-button>
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

    <tickPassengers-modal ref="modalForm" @ok="modalFormOk"></tickPassengers-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TickPassengersModal from './modules/TickPassengersModal'

  export default {
    name: "TickPassengersList",
    mixins:[JeecgListMixin],
    components: {
      TickPassengersModal
    },
    data () {
      return {
        description: 'tick_passengers管理页面',
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
            title:'订单详情编号',
            align:"center",
            dataIndex: 'orderDetailId'
          },
          {
            title:'票号',
            align:"center",
            dataIndex: 'ticketNo'
          },
          {
            title:'名字',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'电话',
            align:"center",
            dataIndex: 'mobile'
          },
          {
            title:'证件类型',
            align:"center",
            dataIndex: 'cardType'
          },
          {
            title:'证件编号',
            align:"center",
            dataIndex: 'cardNo'
          },
          {
            title:'状态；1待使用 2已核验 3.已退',
            align:"center",
            dataIndex: 'status'
          },
          {
            title:'票号生成的二维码地址',
            align:"center",
            dataIndex: 'codeUrl'
          },
          {
            title:'非实名制时，用他来区别出行人',
            align:"center",
            dataIndex: 'replaceCardNo'
          },
          {
            title:'二次核验状态 0-未二次核验 1-已二次核验',
            align:"center",
            dataIndex: 'doubleCheckStatus'
          },
          {
            title:'一次核验时间',
            align:"center",
            dataIndex: 'checkTime'
          },
          {
            title:'取消/退款时间',
            align:"center",
            dataIndex: 'cancelTime'
          },
          {
            title:'二次核验时间',
            align:"center",
            dataIndex: 'doubleCheckTime'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/datatickpassengers/tickPassengers/list",
          delete: "/datatickpassengers/tickPassengers/delete",
          deleteBatch: "/datatickpassengers/tickPassengers/deleteBatch",
          exportXlsUrl: "/datatickpassengers/tickPassengers/exportXls",
          importExcelUrl: "datatickpassengers/tickPassengers/importExcel",
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