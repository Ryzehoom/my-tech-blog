<template>
  <div class="min-h-screen text-white font-sans">
    <HeaderBar />
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script setup lang="ts">
import HeaderBar from './components/layout/HeaderBar.vue';
</script>

<style>
/* Global styles are imported in main.ts via style.css */
</style>
