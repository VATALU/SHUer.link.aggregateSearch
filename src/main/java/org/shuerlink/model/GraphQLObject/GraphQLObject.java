package org.shuerlink.model.GraphQLObject;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class GraphQLObject {
    public static GraphQLObjectType wallpaperCategory = newObject().name("wallpaperCategory")
            .field(newFieldDefinition().name("SHU").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("others").type(Scalars.GraphQLString).build()).build();

    public static GraphQLObjectType shuerlink = newObject().name("shuerlink")
            .field(newFieldDefinition().name("autoChangeWallpaper").type(Scalars.GraphQLBoolean).build())
            .field(newFieldDefinition().name("defaultBackgroundImage").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("defaultSearchEngine").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("theme").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("defaultWikiLanguage").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("changeWallpaperTime").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("autoComplete").type(Scalars.GraphQLBoolean).build())
            .field(newFieldDefinition().name("wallpaperCategory").type(wallpaperCategory).build()).build();

    public static GraphQLObjectType custom = newObject().name("custom")
            .field(newFieldDefinition().name("shuerlink").type(shuerlink).build()).build();

    public static GraphQLObjectType studentInfo = newObject().name("studentInfo")
            .field(newFieldDefinition().name("id").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("Logined").type(Scalars.GraphQLBoolean).build())
            .field(newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("nickname").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("avatar").type(Scalars.GraphQLString).build())
            .field(newFieldDefinition().name("custom").type(custom).build())
            .build();


}
