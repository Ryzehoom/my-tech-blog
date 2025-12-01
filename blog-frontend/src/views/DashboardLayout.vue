<template>
  <div class="min-h-screen flex flex-col">
    <!-- Re-use main header or a specific dashboard header -->
    <!-- For simplicity, we assume the main App.vue header is present, 
         so this layout might just handle the inner content or side nav if we add one. -->
    
    <div class="flex-1 container mx-auto px-4 py-8 flex gap-8">
      <!-- Side Nav (Optional, as per structure suggestion) -->
      <aside class="w-64 hidden md:block">
        <GlassCard class="h-full">
          <nav class="space-y-2">
            <router-link 
              to="/dashboard/articles/new" 
              class="block px-4 py-2 rounded-lg text-gray-300 hover:bg-white/10 hover:text-white transition-colors"
              active-class="bg-white/10 text-white"
            >
              Write Article
            </router-link>
            <!-- Add more dashboard links here -->
          </nav>
        </GlassCard>
      </aside>

      <!-- Main Content -->
      <main class="flex-1">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import GlassCard from '../components/common/GlassCard.vue';
</script>
