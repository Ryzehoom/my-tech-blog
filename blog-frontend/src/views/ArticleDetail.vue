<template>
  <div class="container mx-auto px-4 py-8">
    <div v-if="loading" class="text-center text-gray-400 py-12">
      Loading article...
    </div>
    
    <div v-else-if="error" class="text-center text-red-400 py-12">
      {{ error }}
    </div>
    
    <article v-else-if="article" class="max-w-4xl mx-auto">
      <GlassCard class="p-8 md:p-12">
        <h1 class="text-3xl md:text-4xl font-bold text-white mb-6">{{ article.title }}</h1>
        
        <div class="flex items-center space-x-4 text-sm text-gray-400 mb-8 pb-8 border-b border-white/10">
          <span>{{ formatDate(article.createTime) }}</span>
          <span v-if="article.authorName">By {{ article.authorName }}</span>
        </div>
        
        <div class="prose prose-invert prose-lg max-w-none">
          <!-- In a real app, use a markdown renderer here -->
          <div class="whitespace-pre-wrap font-sans text-gray-200 leading-relaxed">
            {{ article.content }}
          </div>
        </div>
      </GlassCard>
    </article>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { fetchArticleDetailPublic, fetchArticleDetailPrivate } from '../api/article';
import { useAuthStore } from '../stores/auth';
import GlassCard from '../components/common/GlassCard.vue';

const route = useRoute();
const authStore = useAuthStore();
const article = ref<any>(null);
const loading = ref(true);
const error = ref('');

const formatDate = (dateStr: string) => {
  if (!dateStr) return '';
  return new Date(dateStr).toLocaleDateString();
};

onMounted(async () => {
  const id = route.params.id as string;
  if (!id) return;

  try {
    // If logged in, maybe try private endpoint? Or just stick to public for now unless it's a private article.
    // The requirement says: "ArticleDetail.vue: ... call public detail or private detail"
    // We'll try public first, or decide based on some flag. For now, let's just try public.
    // Actually, if the user is the author or if it's a draft, we might need private.
    // Let's assume public for viewing.
    
    let response;
    try {
        response = await fetchArticleDetailPublic(id);
    } catch (e) {
        // If public fails (maybe 403/404), and we are logged in, try private
        if (authStore.token) {
             response = await fetchArticleDetailPrivate(id);
        } else {
            throw e;
        }
    }
    
    article.value = response.data.data || response.data;
  } catch (err) {
    console.error(err);
    error.value = 'Failed to load article.';
  } finally {
    loading.value = false;
  }
});
</script>
