<template>
  <header class="sticky top-0 z-50 backdrop-blur-lg bg-gray-900/80 border-b border-white/10">
    <div class="container mx-auto px-4 h-16 flex items-center justify-between">
      <router-link to="/" class="text-xl font-bold bg-gradient-to-r from-blue-400 to-purple-500 bg-clip-text text-transparent">
        My Tech Blog
      </router-link>
      
      <nav class="flex items-center space-x-6">
        <router-link to="/" class="text-gray-300 hover:text-white transition-colors">Articles</router-link>
        
        <template v-if="authStore.token">
          <router-link to="/dashboard/articles/new" class="text-gray-300 hover:text-white transition-colors">Write</router-link>
          <button @click="handleLogout" class="text-gray-300 hover:text-white transition-colors">Logout</button>
        </template>
        <template v-else>
          <router-link to="/login" class="px-4 py-2 rounded-lg bg-white/10 hover:bg-white/20 transition-colors text-white text-sm font-medium">
            Login
          </router-link>
        </template>
      </nav>
    </div>
  </header>
</template>

<script setup lang="ts">
import { useAuthStore } from '../../stores/auth';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const router = useRouter();

const handleLogout = () => {
  authStore.logout();
  router.push('/login');
};
</script>
