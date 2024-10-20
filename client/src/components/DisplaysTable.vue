<template>
    <section>
      <table id="display-table">
        <thead>
          <tr>
            <th>Drug</th>
            <th>Drug Class</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="display in filteredDisplays" :key="display.id">
            <td>{{ display.drug_name || 'N/A' }}</td>
            <td>{{ display.classes_name || 'N/A' }}</td>
          </tr>
        </tbody>
      </table>
    </section>
  </template>
  
  <script>
  export default {
    computed: {
      filteredDisplays() {
        let filtered = [];
        const currentChoiceId = this.$store.state.currentChoiceId;
        if (currentChoiceId === 1) {
          filtered = this.$store.state.drugs;
        } else if (currentChoiceId === 2) {
          filtered = this.$store.state.classes.map(cls => ({
            id: cls.id,
            classes_name: cls.class_name,
            drug_name: null,
          }));
        } else {
          filtered = this.$store.state.drugs.filter(drug => drug.class_id === currentChoiceId);
        }
        return filtered;
      }
    }
  }
  </script>
  
  <style>
  #display-table th, #display-table td {
    padding: 10px;
  }
  </style>  