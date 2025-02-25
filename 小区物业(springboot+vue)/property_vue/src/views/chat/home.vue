<template>
  <div class="home-container">
    <h1>ShallowSeek</h1>
    <div class="chat-options">
      <button @click="goToChat(true)">物业相关</button>
      <button @click="goToChat(false)">自由聊天</button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goToChat(isPropertyRelated) {
      // 将选项传递到聊天页面
      this.$router.push({ name: 'chat', query: { isPropertyRelated } });
    }
  }
}
</script>

<style>
.home-container {
  width: 80vw;
  height: 60vh;
  margin: auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.chat-options button {
  padding: 15px 25px;
  margin: 10px;
  border: none;
  border-radius: 20px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.chat-options button:hover {
  background-color: #0056b3;
}
</style>
