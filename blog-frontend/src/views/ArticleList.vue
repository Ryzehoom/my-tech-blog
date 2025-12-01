<template>
  <div class="container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-white mb-8">Latest Articles</h1>
    
    <div v-if="loading" class="text-center text-gray-400 py-12">
      Loading articles...
    </div>
    
    <div v-else-if="error" class="text-center text-red-400 py-12">
      {{ error }}
    </div>
    
    <div v-else class="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
      <GlassCard 
        v-for="article in articles" 
        :key="article.id"
        class="hover:bg-white/15 transition-colors cursor-pointer group"
        @click="router.push(`/articles/${article.id}`)"
      >
        <h3 class="text-xl font-semibold text-white mb-2 group-hover:text-blue-400 transition-colors">
          {{ article.title }}
        </h3>
        <p class="text-gray-400 text-sm line-clamp-3 mb-4">
          {{ article.summary || article.content }}
        </p>
        <div class="flex items-center justify-between text-xs text-gray-500">
          <span>{{ formatDate(article.createTime) }}</span>
          <span v-if="article.authorName">By {{ article.authorName }}</span>
        </div>
      </GlassCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { fetchPublicArticles } from '../api/article';
import GlassCard from '../components/common/GlassCard.vue';

const router = useRouter();
const articles = ref<any[]>([]);
const loading = ref(true);
const error = ref('');

const formatDate = (dateStr: string) => {
  if (!dateStr) return '';
  return new Date(dateStr).toLocaleDateString();
};

onMounted(async () => {
  try {
    const response = await fetchPublicArticles();
    // Adjust based on actual API response structure (e.g. response.data.data or response.data)
    articles.value = Array.isArray(response.data) ? response.data : (response.data.data || []);
  } catch (err) {
    console.error(err);
    error.value = 'Failed to load articles.';
  } finally {
    loading.value = false;
  }
});
</script>
