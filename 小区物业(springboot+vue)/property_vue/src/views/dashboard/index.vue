<template>
  <div class="dashboard-container">
    <div class="dashboard-text">welcome! {{ name }}</div>
    <Carousel />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Carousel from './Carousel.vue'

export default {
  name: 'Dashboard',
  components: {
    Carousel
  },
  computed: {
    ...mapGetters(['name'])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
