  <template>
    <div>
      <div style="margin: 10px 0">
        <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0">
        <el-button type="primary" @click="handleAdd" v-if="user.role === 'ROLE_ADMIN'">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定批量删除这些数据吗？"
            @confirm="delBatch"
        >
          <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>

      </div>
      <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="dutyid" label="值日号" width="80"></el-table-column>
        <el-table-column prop="studentid" label="学号"></el-table-column>

        <el-table-column label="操作" width="280" align="center">
          <template slot-scope="scope">
            <!--          <el-button type="primary" @click="selectduty(scope.row.id)">选课</el-button>-->
            <el-button type="success" @click="handleEdit(scope.row)" v-if="user.role === 'ROLE_ADMIN'">编辑 <i class="el-icon-edit"></i></el-button>
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='我再想想'
                icon="el-icon-info"
                icon-color="red"
                title="您确定删除吗？"
                @confirm="del(scope.row.dutyid, scope.row.studentid)"
            >
              <el-button type="danger" slot="reference" v-if="user.role === 'ROLE_ADMIN'">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <div style="padding: 10px 0">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[2, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>

      <el-dialog title="值日人员" :visible.sync="dialogFormVisible" width="30%" >
        <el-form label-width="80px" size="small">
          <el-form-item label="值日号">
            <el-input v-model="form.dutyid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="学号">
            <el-input v-model="form.studentid" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </template>

  <script>

  import row from "element-ui/packages/row";

  export default {
    name: "duty",
    data() {
      return {
        form: {},
        tableData: [],
        name: '',
        multipleSelection: [],
        pageNum: 1,
        pageSize: 10,
        total: 0,
        dialogFormVisible: false,
        teachers: [],
        user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
      }
    },
    created() {
      this.load()
    },
    methods: {
      load() {
        this.request.get("/dutystudent/page", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name,
          }
        }).then(res => {
          console.log("res.code: " + res.code)
          console.log("res.data: " + res.data)
          this.tableData = res.data
          this.total = res.data.length

        })
      }
      ,
      changeEnable(row) {
        this.request.post("/dutystudent/save", row).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          }
        })
      },
      handleAdd() {
        this.dialogFormVisible = true
        this.form = {}
      },
      handleEdit(row) {
        this.form = JSON.parse(JSON.stringify(row))
        this.dialogFormVisible = true
      },

      del(dutyid, studentid) {
        // 检查转换后的值是否有效
        if (isNaN(dutyid) || isNaN(studentid) || dutyid < 0 || studentid < 0) {
          // dutyid 或 studentid 无效，不执行删除操作
          this.$message.error("值日号或学号无效");
          return;
        }

        // 构造请求的 URL
        const url = `/dutystudent/${dutyid}/${studentid}`;

        // 发送删除请求
        this.request.delete(url)
            .then(res => {
              if (res.code === '200') {
                this.$message.success("删除成功");
                this.load();
              } else {
                this.$message.error("删除失败");
              }
            })
            .catch(error => {
              this.$message.error("删除失败");
              console.error(error);
            });
      }




      ,
      handleSelectionChange(val) {
        console.log(val)
        this.multipleSelection = val
      },
      delBatch() {
        let dutyStudents = this.multipleSelection.map(v => {
          return { dutyid: v.dutyid, studentid: v.studentid };
        });

        this.request.post("/dutystudent/del/batch", dutyStudents).then(res => {
          if (res.code === '200') {
            this.$message.success("批量删除成功");
            this.load();
          } else {
            this.$message.error("批量删除失败");
          }
        });
      }
      ,
      save() {
        this.request.post("/dutystudent", this.form).then(res => {
          if (res.code === '200') {
            this.$message.success("保存成功")
            this.dialogFormVisible = false
          } else {
            this.$message.error("保存失败")
          }
          this.load()
        })
      },
      reset() {
        this.name = ""
        this.load()
      },
      handleSizeChange(pageSize) {
        console.log(pageSize)
        this.pageSize = pageSize
        this.load()
      },
      handleCurrentChange(pageNum) {
        console.log(pageNum)
        this.pageNum = pageNum
        this.load()
      },
      download(url) {
        window.open(url)
      },







    }
  }
  </script>

  <style scoped>

  </style>
