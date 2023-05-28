<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 5px">
      <b>欢迎你！{{ user.username }}</b>
    </div>
    <el-card>
      这是海南大学2021级计算机科学与技术3班李季鸿设计的班主任工作管理系统
      <div style="color: #fd6b6b; font-size:25px; padding-top: 0px"><b>功能描述：</b></div>
      <div><b>(1)基本要求</b></div>
      中学班主任的日常工作非常繁琐，需要管理学生相关信息和班级的日常活动等。结合班主任工作实际，开发一个管理系统。
      <br><br/>
      <div><b>(2)基本功能</b></div>
      <div>①学生资料管理：提供学生基本档案、学生评语、家访记录的维护，可按照年龄、性别、民族、政治面貌、职务等查询和统计学生信息。</div>
      <div>②班级日常管理：提供班干部管理、宿舍管理、考勤管理、班级课程表、班级活动、班级奖惩记录、缴费记录、班级值日信息的维护功能。</div>
      <div>③成绩管理：提供成绩录入、成绩汇总统计等功能。统计学生成绩分布、平均分、总分、班级名次等信息。</div>
      <div>④班级工作管理：提供班主任工作计划、班主任工作总结、班级主题班会、学生谈话记录的维护功能。</div>
      <div>⑤系统管理；提供参数设置、评语库维护、密码修改等功能。</div>
      <br><br/>
      <div style="color: #3F5EFB; padding-bottom: 5px"><b>李季鸿的个人Github: <a href="https://github.com/Li-Jihong" target="_blank">https://github.com/Li-Jihong</a></b></div>
      <div style="color: #3F5EFB; padding-bottom: 5px"><b>李季鸿的个人Github: <a href="https://github.com/Li-Jihong" target="_blank">2182726049</a></b></div>
      <div style="color: #3F5EFB; padding-bottom: 5px"><b>李季鸿的个人邮箱: <a href="https://github.com/Li-Jihong" target="_blank">20213002624@qq.com</a></b></div>
    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>
