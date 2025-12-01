<template>
  <div class="max-w-4xl mx-auto">
    <GlassCard>
      <h2 class="text-2xl font-bold text-white mb-6">Write New Article</h2>
      
      <form @submit.prevent="handleSubmit" class="space-y-6">
        <div>
          <label class="block text-gray-300 text-sm font-medium mb-2">Title</label>
          <input 
            v-model="form.title"
            type="text" 
            class="w-full bg-white/5 border border-white/10 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 transition-colors"
            placeholder="Article Title"
            required
          />
        </div>

        <div>
          <label class="block text-gray-300 text-sm font-medium mb-2">Summary</label>
          <textarea 
            v-model="form.summary"
            rows="3"
            class="w-full bg-white/5 border border-white/10 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 transition-colors"
            placeholder="Brief summary..."
          ></textarea>
        </div>

        <div>
          <label class="block text-gray-300 text-sm font-medium mb-2">Content</label>
          <textarea 
            v-model="form.content"
            rows="15"
            class="w-full bg-white/5 border border-white/10 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 transition-colors font-mono"
            placeholder="Write your article content here..."
            required
          ></textarea>
        </div>

        <div class="flex justify-end space-x-4">
          <button 
            type="button" 
            @click="router.back()"
            class="px-6 py-2 rounded-lg text-gray-300 hover:text-white hover:bg-white/10 transition-colors"
          >
            Cancel
          </button>
          <button 
            type="submit" 
            :disabled="loading"
            class="px-6 py-2 rounded-lg bg-gradient-to-r from-blue-500 to-purple-600 text-white font-medium hover:opacity-90 transition-opacity disabled:opacity-50"
          >
            {{ loading ? 'Publishing...' : 'Publish' }}
          </button>
        </div>
      </form>
    </GlassCard>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { createArticle } from '../api/article';
import GlassCard from '../components/common/GlassCard.vue';

const router = useRouter();
const loading = ref(false);

const form = ref({
  title: '',
  summary: '',
  content: ''
});

const handleSubmit = async () => {
  loading.value = true;
  try {
    await createArticle(form.value);
    router.push('/');
  } catch (err) {
    console.error(err);
    alert('Failed to create article');
  } finally {
    loading.value = false;
  }
};
</script>
