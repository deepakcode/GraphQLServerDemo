# graphql-playground

https://github.com/graphql/graphql-playground

`$ brew install --cask graphql-playground`


<details>
  <summary>GraphQL+Srping</summary></summary>


```java
@Component
public class GraphQLProvider {

    @Autowired
    DataFetcher laptopDataFetcher;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL()
    {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException
    {
      URL url = Resources.getResource("schema.graphqls");
      String schemaString = Resources.toString(url, Charsets.UTF_8);
      GraphQLSchema graphQLSchema = buildSchema(schemaString);
      this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    private GraphQLSchema buildSchema(String schemaString)
    {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaString);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return  schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring()
    {
        return RuntimeWiring.newRuntimeWiring()
                .type(mutationBuilder())
                .type(queryBuilder())
                .build();
    }

    private TypeRuntimeWiring.Builder mutationBuilder() {
        return TypeRuntimeWiring.newTypeWiring("Mutation")
                .dataFetcher("deleteLaptop", laptopDataFetcher.deleteLaptop());
    }

    private TypeRuntimeWiring.Builder queryBuilder()
    {
        return TypeRuntimeWiring.newTypeWiring("Query")
                .dataFetcher("getAllLaptops", laptopDataFetcher.getAllLaptops())
                .dataFetcher("getLaptopByID", laptopDataFetcher.getLaptopByID())
                .dataFetcher("getLaptopsLessThan", laptopDataFetcher.getLaptopsLessThan());

    }

}
```
</details>
