<template>
  <div class="max-w-[677px] mx-auto px-4 py-8">
    <div v-if="loading" class="text-center text-gray-400 py-12">
      Loading articles...
    </div>
    
    <div v-else-if="error" class="text-center text-red-400 py-12">
      {{ error }}
    </div>
    
    <div v-else class="space-y-6">
      <div 
        v-for="article in articles" 
        :key="article.id"
        class="bg-white rounded-lg overflow-hidden shadow-sm border border-gray-100 cursor-pointer hover:shadow-md transition-shadow"
        @click="router.push(`/articles/${article.id}`)"
      >
        <!-- Cover Image -->
        <div class="h-48 bg-gray-200 w-full relative overflow-hidden">
           <img v-if="article.coverImage" :src="article.coverImage" class="w-full h-full object-cover" />
           <div v-else class="absolute inset-0 flex items-center justify-center text-gray-400">
             <span class="text-4xl">📷</span>
           </div>
           <div class="absolute bottom-0 left-0 right-0 p-4 bg-gradient-to-t from-black/60 to-transparent">
             <h3 class="text-lg font-bold text-white line-clamp-2">
               {{ article.title }}
             </h3>
           </div>
        </div>
        
        <div class="p-4">
          <p class="text-gray-500 text-sm line-clamp-2 mb-3">
            {{ article.summary || article.content }}
          </p>
          <div class="flex items-center justify-between text-xs text-gray-400">
            <span>{{ formatDate(article.createTime) }}</span>
            <span class="text-[#576b95]">Read More</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { fetchPublicArticles } from '../api/article';

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
