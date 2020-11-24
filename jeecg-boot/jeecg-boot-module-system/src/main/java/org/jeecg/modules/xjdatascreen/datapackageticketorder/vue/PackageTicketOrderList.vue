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
      <a-button type="primary" icon="download" @click="handleExportXls('package_ticket_order')">导出</a-button>
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

    <packageTicketOrder-modal ref="modalForm" @ok="modalFormOk"></packageTicketOrder-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import PackageTicketOrderModal from './modules/PackageTicketOrderModal'

  export default {
    name: "PackageTicketOrderList",
    mixins:[JeecgListMixin],
    components: {
      PackageTicketOrderModal
    },
    data () {
      return {
        description: 'package_ticket_order管理页面',
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
            title:'套票订单号',
            align:"center",
            dataIndex: 'orderNum'
          },
          {
            title:'景区编号',
            align:"center",
            dataIndex: 'scenicNum'
          },
          {
            title:'订单金额',
            align:"center",
            dataIndex: 'orderAmount'
          },
          {
            title:'订单状态',
            align:"center",
            dataIndex: 'status'
          },
          {
            title:'订单支付状态',
            align:"center",
            dataIndex: 'payStatus'
          },
          {
            title:'订单创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title:'订单来源',
            align:"center",
            dataIndex: 'orderChannel'
          },
          {
            title:'支付方式',
            align:"center",
            dataIndex: 'paymentModel'
          },
          {
            title:'使用日期',
            align:"center",
            dataIndex: 'useDate'
          },
          {
            title:'联系人名称',
            align:"center",
            dataIndex: 'contactName'
          },
          {
            title:'联系人手机',
            align:"center",
            dataIndex: 'contactMobile'
          },
          {
            title:'联系人证件类型',
            align:"center",
            dataIndex: 'contactCardType'
          },
          {
            title:'联系人证件号',
            align:"center",
            dataIndex: 'contactCardNo'
          },
          {
            title:'用户唯一标识',
            align:"center",
            dataIndex: 'owner'
          },
          {
            title:'取票号',
            align:"center",
            dataIndex: 'pickupTicketNo'
          },
          {
            title:'支付时间',
            align:"center",
            dataIndex: 'payTime'
          },
          {
            title:'支付订单号',
            align:"center",
            dataIndex: 'payOrderId'
          },
          {
            title:'产品名称',
            align:"center",
            dataIndex: 'productName'
          },
          {
            title:'数量',
            align:"center",
            dataIndex: 'orderCount'
          },
          {
            title:'产品编号',
            align:"center",
            dataIndex: 'productCode'
          },
          {
            title:'用户是否删除标识 0-未删除 1-已删除',
            align:"center",
            dataIndex: 'isDelete'
          },
          {
            title:'套票单价',
            align:"center",
            dataIndex: 'unitPrice'
          },
          {
            title:'订单类型',
            align:"center",
            dataIndex: 'orderType'
          },
          {
            title:'已核验数量',
            align:"center",
            dataIndex: 'useCount'
          },
          {
            title:'已退数量',
            align:"center",
            dataIndex: 'cancelCount'
          },
          {
            title:'有效使用结束日期',
            align:"center",
            dataIndex: 'endDate'
          },
          {
            title:'分时库存开始时间',
            align:"center",
            dataIndex: 'startTime'
          },
          {
            title:'分时库存结束时间',
            align:"center",
            dataIndex: 'endTime'
          },
          {
            title:'售后订单状态',
            align:"center",
            dataIndex: 'afterSaleStatus'
          },
          {
            title:'订单完结时间',
            align:"center",
            dataIndex: 'checkFinishTime'
          },
          {
            title:'商家编号（订单中商品是哪个商家的',
            align:"center",
            dataIndex: 'merchantCode'
          },
          {
            title:'已退款金额',
            align:"center",
            dataIndex: 'refundAmount'
          },
          {
            title:'客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源',
            align:"center",
            dataIndex: 'orderCustomerName'
          },
          {
            title:'客户类型;T为分销商；C为散客编号为空；',
            align:"center",
            dataIndex: 'orderCustomerType'
          },
          {
            title:'分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号',
            align:"center",
            dataIndex: 'orderCustomerNo'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/datapackageticketorder/packageTicketOrder/list",
          delete: "/datapackageticketorder/packageTicketOrder/delete",
          deleteBatch: "/datapackageticketorder/packageTicketOrder/deleteBatch",
          exportXlsUrl: "/datapackageticketorder/packageTicketOrder/exportXls",
          importExcelUrl: "datapackageticketorder/packageTicketOrder/importExcel",
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