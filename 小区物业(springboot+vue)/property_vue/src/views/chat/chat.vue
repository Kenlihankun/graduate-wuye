<template>
  <div class="chat-container">
    <h1>与 GPT 对话</h1>
    <div class="messages">
      <div v-for="(message, index) in messages" :key="index" class="message">
        <strong>{{ message.sender }}:</strong> {{ message.text }}
      </div>
    </div>
    <input
      v-model="userInput"
      @keyup.enter="sendMessage"
      placeholder="输入你的消息..."
    />
    <button @click="sendMessage">发送</button>
  </div>
</template>

<script>
import { getChat } from "@/api/chat";

export default {
  data() {
    return {
      userInput: '',
      messages: [],
    };
  },
  methods: {
    async sendMessage() {
      if (!this.userInput) return;

      // 添加用户消息
      this.messages.push({ sender: '用户', text: this.userInput });

      try {
        console.log('发送的消息:', this.userInput);
        // 调用 getChat 方法并传递参数
        const response = await getChat(this.userInput); // 直接传递用户输入
        console.log('返回信息:', response);
        // 检查响应的 code
        if (response.code === 200) {
          // 添加 GPT 返回的消息
          this.messages.push({ sender: 'GPT', text: response.msg });
        } else {
          // 如果 code 不是 200，可以处理错误或提示
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
  max-width: 600px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.messages {
  max-height: 400px;
  overflow-y: auto;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 4px;
}
.message {
  margin-bottom: 5px;
}
input {
  width: calc(100% - 80px);
  padding: 10px;
}
button {
  padding: 10px;
}
</style>
