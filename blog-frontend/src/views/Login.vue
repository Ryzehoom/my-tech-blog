<template>
  <div class="min-h-[calc(100vh-64px)] flex items-center justify-center px-4">
    <GlassCard class="w-full max-w-md">
      <h2 class="text-2xl font-bold text-white mb-6 text-center">Welcome Back</h2>
      
      <form @submit.prevent="handleLogin" class="space-y-4">
        <div>
          <label class="block text-gray-300 text-sm font-medium mb-2">Username</label>
          <input 
            v-model="username"
            type="text" 
            class="w-full bg-white/5 border border-white/10 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 transition-colors"
            placeholder="Enter your username"
            required
          />
        </div>
        
        <div>
          <label class="block text-gray-300 text-sm font-medium mb-2">Password</label>
          <input 
            v-model="password"
            type="password" 
            class="w-full bg-white/5 border border-white/10 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 transition-colors"
            placeholder="Enter your password"
            required
          />
        </div>

        <div v-if="error" class="text-red-400 text-sm text-center">
          {{ error }}
        </div>

        <button 
          type="submit" 
          :disabled="loading"
          class="w-full bg-gradient-to-r from-blue-500 to-purple-600 text-white font-medium py-2 rounded-lg hover:opacity-90 transition-opacity disabled:opacity-50"
        >
          {{ loading ? 'Logging in...' : 'Login' }}
        </button>
      </form>
    </GlassCard>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../stores/auth';
import { login } from '../api/user';
import GlassCard from '../components/common/GlassCard.vue';

const username = ref('');
const password = ref('');
const loading = ref(false);
const error = ref('');

const router = useRouter();
const authStore = useAuthStore();

const handleLogin = async () => {
  loading.value = true;
  error.value = '';
  
  try {
    const response = await login({
      username: username.value,
      password: password.value
    });
    
    // Assuming response.data contains the token or user object with token
    // Adjust based on actual API response structure
    const token = response.data.token || response.data; 
    authStore.setToken(token);
    
    router.push('/');
  } catch (err: any) {
    console.error(err);
    error.value = 'Login failed. Please check your credentials.';
  } finally {
    loading.value = false;
  }
};
</script>
