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
      <a-button type="primary" icon="download" @click="handleExportXls('ticket_order_detail')">导出</a-button>
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

    <ticketOrderDetail-modal ref="modalForm" @ok="modalFormOk"></ticketOrderDetail-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TicketOrderDetailModal from './modules/TicketOrderDetailModal'

  export default {
    name: "TicketOrderDetailList",
    mixins:[JeecgListMixin],
    components: {
      TicketOrderDetailModal
    },
    data () {
      return {
        description: 'ticket_order_detail管理页面',
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
            title:'订单编号',
            align:"center",
            dataIndex: 'orderNum'
          },
          {
            title:'票的id',
            align:"center",
            dataIndex: 'ticketId'
          },
          {
            title:'是否一人多票',
            align:"center",
            dataIndex: 'isMore'
          },
          {
            title:'是否实名制',
            align:"center",
            dataIndex: 'isRealName'
          },
          {
            title:'票的产品编号',
            align:"center",
            dataIndex: 'ticketCode'
          },
          {
            title:'单价',
            align:"center",
            dataIndex: 'unitPrice'
          },
          {
            title:'总价',
            align:"center",
            dataIndex: 'totalPrice'
          },
          {
            title:'数量',
            align:"center",
            dataIndex: 'quantity'
          },
          {
            title:'票种名称',
            align:"center",
            dataIndex: 'typeName'
          },
          {
            title:'票的名称',
            align:"center",
            dataIndex: 'ticketName'
          },
          {
            title:'房型名称',
            align:"center",
            dataIndex: 'roomName'
          },
          {
            title:'房型id',
            align:"center",
            dataIndex: 'hotelRoomId'
          },
          {
            title:'房型编号',
            align:"center",
            dataIndex: 'hotelRoomCode'
          },
          {
            title:'使用日期',
            align:"center",
            dataIndex: 'useDate'
          },
          {
            title:'票的日库存id',
            align:"center",
            dataIndex: 'timeDivisionStockId'
          },
          {
            title:'使用数量',
            align:"center",
            dataIndex: 'useCount'
          },
          {
            title:'退的数量',
            align:"center",
            dataIndex: 'cancelCount'
          },
          {
            title:'使用有效截止日期',
            align:"center",
            dataIndex: 'endDate'
          },
          {
            title:'票的上一级编号',
            align:"center",
            dataIndex: 'superTicketCode'
          },
          {
            title:'分时库存时段开始时间 HH:mm',
            align:"center",
            dataIndex: 'startTime'
          },
          {
            title:'分时库存时段结束时间 HH:mm',
            align:"center",
            dataIndex: 'endTime'
          },
          {
            title:'二次核验的数量',
            align:"center",
            dataIndex: 'doubleCheckCount'
          },
          {
            title:'已退款金额',
            align:"center",
            dataIndex: 'refundAmount'
          },
          {
            title:'OTA售卖给用户的价格',
            align:"center",
            dataIndex: 'otaBuyPrice'
          },
          {
            title:'OTA产品编号',
            align:"center",
            dataIndex: 'otaProductCode'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/dataticketorderdetail/ticketOrderDetail/list",
          delete: "/dataticketorderdetail/ticketOrderDetail/delete",
          deleteBatch: "/dataticketorderdetail/ticketOrderDetail/deleteBatch",
          exportXlsUrl: "/dataticketorderdetail/ticketOrderDetail/exportXls",
          importExcelUrl: "dataticketorderdetail/ticketOrderDetail/importExcel",
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