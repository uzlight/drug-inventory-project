<template>
    <div v-if="drug">
      <h2>{{ drug.drug_name }}</h2>
      <p>Cost: ${{ drug.cost }}</p>
      <p>Availability: {{ drug.is_available ? 'Available' : 'Not Available' }}</p>
      <p> Class: <router-link :to="{ name: 'ClassDetail', params: { id: drug.class_id } }">
          {{ className }}
        </router-link>
      </p>
      <button @click="$router.back()">Back</button>
    </div>
    <div v-else>
      <p>Loading drug details...</p>
    </div>
  </template>

<script>
import { resourceService } from '../services/ResourceService.js';

export default {
  name: 'DrugDetailView',
  props: ['id'],
  data() {
    return {
      drug: null,
      className: '',
      error: null,
    };

  },
  created() {
    this.getDrugDetails();
  },

  methods: {
    getDrugDetails() {
      resourceService.getDrugById(this.id)
        .then(response => {
          this.drug = response.data;
          return resourceService.getClassById(this.drug.class_id);
        })
        .then(response => {
          this.className = response.data.class_name;
        })
    },
  },
};
</script>

<style>

h2 {
  font-size: 2em;
}
p {
  font-size: 1.2em;
}
button {
  margin-top: 20px;
}


</style>