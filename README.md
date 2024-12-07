## 基于Web的成绩管理系统

### 一、实验目的

1. 本次实验的目的是掌握Java企业级应用开发工具的使用方法；掌握Java Web编程技术；掌握常用Java后端开发框架的使用，例如SpringBoot等；理解所用框架的工作原理。

2. 设计开发完成一个基于Web的成绩管理系统。

3. 抄袭计0分。

### 二、实验项目内容

设计开发完成一个基于Web的成绩管理系统。要求如下：

1、后端可以使用SpringBoot等框架，**也可以使用其他框架**。要求数据存储在数据库中。自由选择使用什么数据库。

2、前端使用浏览器访问软件系统界面。**自由选择**前端界面技术。自由选择是否前后端分离架构。

3、提供账号注册、登录、注销等功能。教师账号管理学生成绩，学生账号查看学生成绩(√)。教务账号管理班级，课程等。

4、创建类，实现基本对象和他们关系的管理。包括学生、教学班、课程、成绩、教师等。学生至少包含学号、姓名、性别等信息。教学班至少包含教师、课程名字、总人数、教学班号、开课学期等信息。课程至少包含课程编号、课程名字等信息。教师至少包含教师编号、姓名等信息。可以根据自己的分析设计增加其他类。（√）

5、随机生成学生，数量不少于100。课程数量不少于3门。一个教学班有一个教师上一门课程，一个教学班的学生数量不少于20。教师数量不少于6个。一门课至少有两个老师上课。每个学生至少选择3门课程。一个学生在一个教学班上一门课，考试后取得一个成绩。一门课的成绩构成有4部分构成，包括平时成绩、期中考试、实验成绩和期末考试成绩，然后计算出综合成绩。自定义各项成绩的产生策略，均为整数。

6、提供合适的操作界面完成上述功能。可以录入成绩，可以批量产生成绩等（√）；能够格式规范地显示一个教学班的学生的成绩，可以根据学号排序，可以根据成绩排序。（√）可以统计学生各科、总成绩的分数段分布。可以通过名字或者学号查询一个学生的所有科目的成绩和总成绩。可以按照学号、各科成绩和总成绩对所有学生进行排名显示。（√）

7、可以实现自己的扩展功能或自定义功能。注意操作使用的方便性，注意类和类之间的关系。充分利用继承，多态等特性，使用上抽象类，接口，泛型，内部类等设计元素，使用好集合类。注意程序的总执行流程和分支执行流程。注意设计思想的表达，注意优化代码结构，优化类的职责分工，注意使用各种框架。代码有注释。

8、在报告中注明自己的创新点、特色等。



### 三、实验过程或算法



```
<template>
  <div class="teacher-dashboard">
    <aside class="sidebar">
      <ul>
        <li @click="viewSection('gradeManagement')">成绩管理</li>
        <li @click="viewSection('statistics')">成绩统计</li>
        <li @click="viewSection('studentQuery')">学生成绩查询</li>
      </ul>
    </aside>
    
    <main class="content">
      <div v-if="currentSection === 'gradeManagement'">
        <h2>成绩管理</h2>
        
        <!-- 学生成绩表格 -->
        <div class="search-container">
          <input v-model="searchQuery" type="text" placeholder="搜索学号或姓名" @input="filterGrades" />
        </div>

        <table>
          <thead>
            <tr>
              <th @click="sortGrades('id')">学号 <span :class="getSortClass('id')"></span></th>
              <th @click="sortGrades('name')">姓名 <span :class="getSortClass('name')"></span></th>
              <th @click="sortGrades('regularScore')">平时成绩 <span :class="getSortClass('regularScore')"></span></th>
              <th @click="sortGrades('midtermScore')">期中成绩 <span :class="getSortClass('midtermScore')"></span></th>
              <th @click="sortGrades('experimentScore')">实验成绩 <span :class="getSortClass('experimentScore')"></span></th>
              <th @click="sortGrades('finalScore')">期末成绩 <span :class="getSortClass('finalScore')"></span></th>
              <th @click="sortGrades('comprehensiveScore')">综合成绩 <span :class="getSortClass('comprehensiveScore')"></span></th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(grade, index) in paginatedGrades" :key="index">
              <td>{{ grade.id }}</td>
              <td>{{ grade.name }}</td>
              <td>{{ grade.regularScore }}</td>
              <td>{{ grade.midtermScore }}</td>
              <td>{{ grade.experimentScore }}</td>
              <td>{{ grade.finalScore }}</td>
              <td>{{ grade.comprehensiveScore }}</td>
              <td><button @click="editGrade(grade)">编辑</button></td>
            </tr>
          </tbody>
        </table>

        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1">上一页</button>
          <span>第 {{ currentPage }} 页，共 {{ totalPages }} 页</span>
          <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
        </div>
      </div>

      <div v-if="currentSection === 'statistics'">
        <h2>成绩统计</h2>
        <div class="statistical-data">
          <div v-for="subject in subjects" :key="subject" class="subject-statistics">
            <h3>{{ subject }} 分布</h3>
            <ul>
              <li v-for="(range, index) in scoreRanges(subject)" :key="index">
                {{ range }}: {{ scoreDistribution(subject, range) }} 人
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div v-if="currentSection === 'studentQuery'">
        <h2>学生成绩查询</h2>
        <div class="search-container">
          <input v-model="queryIdOrName" type="text" placeholder="输入学号或姓名查询" @input="queryStudent" />
        </div>
        <div v-if="studentQueryResult">
          <p>学号: {{ studentQueryResult.id }}</p>
          <p>姓名: {{ studentQueryResult.name }}</p>
          <p>成绩: {{ studentQueryResult.comprehensiveScore }}</p>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';

// 状态管理
const currentSection = ref('gradeManagement');
const searchQuery = ref('');
const queryIdOrName = ref('');
const studentQueryResult = ref(null);
const grades = ref([]);
const subjects = ['数学', '英语', '物理']; // 假设科目
const totalPages = ref(1);
const currentPage = ref(1);
const itemsPerPage = 10;

// 获取成绩数据
const getGrades = async () => {
  try {
    const response = await axios.get('http://localhost:8080/getAllGrades');
    grades.value = response.data.grades;
    totalPages.value = Math.ceil(grades.value.length / itemsPerPage);
  } catch (error) {
    console.error('Error fetching grades:', error);
  }
};

// 排序功能
const sortKey = ref('');
const sortOrder = ref(1);

const sortGrades = (key) => {
  if (sortKey.value === key) {
    sortOrder.value = -sortOrder.value;
  } else {
    sortKey.value = key;
    sortOrder.value = 1;
  }
  grades.value.sort((a, b) => {
    if (a[key] < b[key]) return -sortOrder.value;
    if (a[key] > b[key]) return sortOrder.value;
    return 0;
  });
};

// 获取排序图标类
const getSortClass = (key) => {
  if (sortKey.value === key) {
    return sortOrder.value === 1 ? 'asc' : 'desc';
  }
  return '';
};

// 筛选功能
const filterGrades = () => {
  const query = searchQuery.value.toLowerCase();
  grades.value = grades.value.filter(grade =>
    grade.id.toLowerCase().includes(query) ||
    grade.name.toLowerCase().includes(query)
  );
  totalPages.value = Math.ceil(grades.value.length / itemsPerPage);
  currentPage.value = 1;
};

// 分页计算
const paginatedGrades = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return grades.value.slice(start, end);
});

// 分页控制
const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

// 学生成绩查询
const queryStudent = async () => {
  try {
    const response = await axios.get('http://localhost:8080/getStudentByQuery', {
      params: {
        query: queryIdOrName.value
      }
    });
    studentQueryResult.value = response.data.student;
  } catch (error) {
    console.error('Error fetching student:', error);
  }
};

// 分数段统计
const scoreRanges = (subject) => {
  return ['0-60', '61-80', '81-100']; // 简单示例
};

const scoreDistribution = (subject, range) => {
  return grades.value.filter(grade => {
    const score = grade[subject];
    const [min, max] = range.split('-').map(Number);
    return score >= min && score <= max;
  }).length;
};

// 编辑成绩
const editGrade = (grade) => {
  // 打开编辑页面或者弹出框
};

onMounted(() => {
  getGrades();
});

// 切换视图
const viewSection = (section) => {
  currentSection.value = section;
};
</script>

<style scoped>
.teacher-dashboard {
  display: flex;
}

.sidebar {
  width: 150px;
  background-color: #f4f4f4;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar li {
  padding: 10px;
  margin-bottom: 10px;
  background-color: #2196F3;
  color: white;
  cursor: pointer;
}

.sidebar li:hover {
  background-color: #1976D2;
}

.content {
  flex: 1;
  padding: 20px;
}

h2 {
  margin-top: 0;
}

.search-container input {
  padding: 5px;
  width: 200px;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid #ddd;
}

th, td {
  padding: 10px;
  text-align: center;
}

th {
  cursor: pointer;
}

th .asc::after {
  content: '▲';
  margin-left: 5px;
}

th .desc::after {
  content: '▼';
  margin-left: 5px;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.pagination button {
  padding: 5px 10px;
  margin: 0 5px;
}

.pagination span {
  margin: 0 10px;
}
</style>

```

