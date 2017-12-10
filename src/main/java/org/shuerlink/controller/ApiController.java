package org.shuerlink.controller;

import com.alibaba.fastjson.JSONObject;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLSchema;
import org.shuerlink.model.GraphQLObject.GraphQLObject;
import org.shuerlink.serviceImpl.SHULoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

@Controller

public class ApiController {
    @Resource
    private SHULoginServiceImpl shuLoginService;

    @RequestMapping(value = "/api", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    String loginSHUStudent(@RequestParam(name = "login") String login) {
        GraphQLFieldDefinition loginField = GraphQLFieldDefinition.newFieldDefinition().name("login")
                .argument(newArgument().name("username").type(Scalars.GraphQLString).build())
                .argument(newArgument().name("password").type(Scalars.GraphQLString).build())
                .type(GraphQLObject.studentInfo)
                .dataFetcher(environment -> {
                    String userName = environment.getArgument("username");
                    String password = environment.getArgument("password");
                    return shuLoginService.loginSHUStudent(userName, password);
                }).build();

        GraphQLSchema schema = GraphQLSchema.newSchema().query(newObject()
                .name("api")
                .field(loginField)
                .build()).build();

        ExecutionResult re = GraphQL.newGraphQL(schema).build().execute(login);
        Map<String, Object> result = re.getData();
        JSONObject json = new JSONObject();
        json.putAll(result);

        return json.toString();
    }
}

