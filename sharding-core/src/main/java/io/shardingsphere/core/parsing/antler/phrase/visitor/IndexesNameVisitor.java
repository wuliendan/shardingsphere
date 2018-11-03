/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antler.phrase.visitor;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.shardingsphere.core.parsing.antler.utils.VisitorUtils;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.ddl.DDLStatement;
import io.shardingsphere.core.parsing.parser.token.IndexToken;

/**
 * Visit Multiple index name phrase.
 * 
 * @author duhongjun
 */
public class IndexesNameVisitor implements PhraseVisitor {

    /** 
     * Visit indexes name table node.
     * 
     * @param ancestorNode ancestor node of ast
     * @param statement SQL statement
     */
    @Override
    public void visit(final ParserRuleContext ancestorNode, final SQLStatement statement) {
        DDLStatement ddlStatement = (DDLStatement) statement;
        String tableName = null;
        if (!ddlStatement.getTables().isEmpty()) {
            tableName = ddlStatement.getTables().getSingleTableName();
        }
        List<IndexToken> indicesToken = VisitorUtils.visitIndices(ancestorNode, tableName);
        statement.getSQLTokens().addAll(indicesToken);
    }
}
