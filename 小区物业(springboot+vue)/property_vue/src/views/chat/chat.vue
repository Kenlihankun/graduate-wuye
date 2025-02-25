<template>
  <div class="chat-container">
    <h1>ShallowSeek</h1>
    <div class="messages">
      <div
        v-for="(message, index) in messages"
        :key="index"
        class="message"
        :class="{'user-message': message.sender === '用户', 'gpt-message': message.sender === 'GPT'}"
      >
        <strong>{{ message.sender }}:</strong> {{ message.text }}
      </div>
    </div>
    <div class="input-container">
      <input
        v-model="userInput"
        @keyup.enter="sendMessage"
        placeholder="输入你的消息..."
      />
      <button @click="sendMessage">发送</button>
    </div>
  </div>
</template>

<script>
import { getChat } from "@/api/chat";

export default {
  data() {
    return {
      userInput: '',
      messages: [],
      isPropertyRelated: false,  // 用来存储查询参数
    };
  },
  created() {
    // 获取传递的查询参数并进行类型转换
    console.log('测试:', this.$route.query.isPropertyRelated); // 打印布尔值
    this.isPropertyRelated = this.$route.query.isPropertyRelated === true;
    console.log('物业相关:', this.isPropertyRelated); // 打印布尔值
  },
  methods: {
    async sendMessage() {
      if (!this.userInput) return;

      // 添加用户消息
      this.messages.push({ sender: '用户', text: this.userInput });

      try {
        console.log('发送的消息:', this.userInput);
        const response = await getChat(this.userInput,this.isPropertyRelated);
        console.log('返回信息:', response);
        if (response.code === 200) {
          this.messages.push({ sender: 'GPT', text: response.msg });
        } else {
          this.messages.push({ sender: 'GPT', text: '发生错误，请重试。' });
        }
      } catch (error) {
        console.error('请求失败:', error);
        this.messages.push({ sender: 'GPT', text: '请求失败，请检查网络连接。' });
      } finally {
        this.userInput = '';
      }
    },
  },
};
</script>

<style>
.chat-container {
  width: 85vw; /* 设置为 80% 的视口宽度 */
  height: 90vh; /* 设置为 90% 的视口高度 */
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
}

.messages {
  flex: 1; /* 使消息区域填满剩余空间 */
  overflow-y: auto; /* 允许垂直滚动 */
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 4px;
  background-color: #fff;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 15px;
  word-wrap: break-word;
  max-width: 40%; /* 最大宽度为 80% */
}

.user-message {
  background-color: #e1ffc7;
  align-self: flex-end; /* 用户消息右对齐 */
  margin-left: auto; /* 确保用户消息靠右 */
}

.gpt-message {
  background-color: #d1e8ff;
  align-self: flex-start; /* GPT 消息左对齐 */
  margin-right: auto; /* 确保 GPT 消息靠左 */
}

.input-container {
  display: flex;
  align-items: center;
}

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 20px;
  margin-right: 10px;
}

button {
  padding: 10px 15px;
  border: none;
  border-radius: 20px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
