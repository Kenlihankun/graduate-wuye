<template>
  <div class="carousel-container">
    <div class="carousel" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
      <div
        class="carousel-slide"
        v-for="(image, index) in images"
        :key="index"
      >
        <img :src="image" alt="Carousel Image" />
      </div>
    </div>
    <button class="prev" @click="prevSlide">&#10094;</button>
    <button class="next" @click="nextSlide">&#10095;</button>
    <div class="indicators">
      <span
        v-for="(image, index) in images"
        :key="index"
        :class="{ active: index === currentIndex }"
        @click="goToSlide(index)"
      ></span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentIndex: 0,
      images: [
        require('@/assets/wuye/1.jpg'),
        require('@/assets/wuye/2.jpg'),
        require('@/assets/wuye/3.jpg'),
      ],
    }
  },
  methods: {
    nextSlide() {
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
    },
    prevSlide() {
      this.currentIndex = (this.currentIndex - 1 + this.images.length) % this.images.length;
    },
    goToSlide(index) {
      this.currentIndex = index;
    }
  },
  mounted() {
    setInterval(this.nextSlide, 5000); // 自动切换每5秒
  }
}
</script>

<style lang="scss" scoped>
.carousel-container {
  position: relative;
  max-width: 80%;
  margin: auto;
  overflow: hidden;

  .carousel {
    display: flex;
    transition: transform 0.5s ease;

    .carousel-slide {
      min-width: 100%;
      img {
        width: 100%;
        height: 80%;
      }
    }
  }

  .prev,
  .next {
    position: absolute;
    top: 100%;
    transform: translateY(-50%);
    background-color: rgba(255, 255, 255, 0.7);
    border: none;
    padding: 10px;
    cursor: pointer;
    z-index: 10;

    &:hover {
      background-color: rgba(255, 255, 255, 1);
    }
  }

  .prev {
    left: 10px;
  }

  .next {
    right: 10px;
  }

  .indicators {
    text-align: center;
    margin-top: 10px;

    span {
      display: inline-block;
      width: 10px;
      height: 10px;
      margin: 0 5px;
      border-radius: 50%;
      background-color: lightgray;
      cursor: pointer;

      &.active {
        background-color: gray;
      }
    }
  }
}
</style>
