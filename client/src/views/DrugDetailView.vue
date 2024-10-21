<template>
    <div v-if="drug">
      <h2>{{ drug.name }}</h2>
      <p>Cost: ${{ drug.cost }}</p>
      <p>Availability: {{ drug.available ? 'Available' : 'Not Available' }}</p>
      <p> Class: <router-link :to="{ name: 'ClassDetail', params: { id: drug.classId } }">
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
      console.log("hello3", this.id)
      resourceService.getDrugById(this.id)
        .then(response => {
          console.log("response", response.data)
          this.drug = response.data;
          return resourceService.getClassById(this.drug.classId);
        })
        .then(response => {
          console.log("response2!", response.data)
          this.className = response.data.name;
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