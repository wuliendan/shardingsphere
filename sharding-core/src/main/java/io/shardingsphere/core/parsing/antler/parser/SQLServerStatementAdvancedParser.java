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

package io.shardingsphere.core.parsing.antler.parser;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import io.shardingsphere.core.parsing.antler.AdvancedErrorStrategy;
import io.shardingsphere.core.parsing.antler.AdvancedParserATNSimulator;
import io.shardingsphere.core.parsing.antler.utils.AntlrUtils;
import io.shardingsphere.parser.antlr.SQLServerStatementParser;

/**
 * SQLServer statement parser.
 * 
 * @author duhongjun
 */
public class SQLServerStatementAdvancedParser extends SQLServerStatementParser {

    public SQLServerStatementAdvancedParser(final TokenStream input) {
        super(input);
        _interp = new AdvancedParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache, ID);
        this._errHandler = new AdvancedErrorStrategy(ID);
    }

    /**
     * Match token by token type.
     *
     * @param tokenType token type
     * @return current matched token
     * @throws RecognitionException mismatch throw exception
    */
    @Override
    public Token match(final int tokenType) throws RecognitionException {
        if (tokenType == Token.EOF) {
            matchedEOF = true;
        }
        
        return AntlrUtils.match(this, tokenType, ID);
    }
}
