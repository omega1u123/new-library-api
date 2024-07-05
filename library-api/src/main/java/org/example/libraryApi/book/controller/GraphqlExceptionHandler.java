package org.example.libraryApi.book.controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.example.libraryApi.book.exceptions.*;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        if (ex instanceof BookNotFoundException
                || ex instanceof BookNotSavedException
                || ex instanceof BookNotUpdatedException
                || ex instanceof BookNotTookException
                || ex instanceof BookNotReturnedException
                || ex instanceof BookNotDeletedException
                || ex instanceof InterruptedException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }

        return null;
    }

}
