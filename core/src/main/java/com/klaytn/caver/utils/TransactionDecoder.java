/*
 * Copyright 2019 The caver-java Authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klaytn.caver.utils;

import com.klaytn.caver.tx.type.*;
import org.web3j.utils.Numeric;

import java.util.HashMap;
import java.util.function.Function;

public class TransactionDecoder {
    private static HashMap<TxType.Type, Function<byte[], AbstractTxType>> typeMap
            = new HashMap<TxType.Type, Function<byte[], AbstractTxType>>() {
        {
            put(TxType.Type.FEE_DELEGATED_ACCOUNT_UPDATE, TxTypeFeeDelegatedAccountUpdate::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_ACCOUNT_UPDATE_WITH_RATIO, TxTypeFeeDelegatedAccountUpdateWithRatio::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_CANCEL, TxTypeFeeDelegatedCancel::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_CANCEL_WITH_RATIO, TxTypeFeeDelegatedCancelWithRatio::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_SMART_CONTRACT_DEPLOY, TxTypeFeeDelegatedSmartContractDeploy::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_SMART_CONTRACT_DEPLOY_WITH_RATIO, TxTypeFeeDelegatedSmartContractDeployWithRatio::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_SMART_CONTRACT_EXECUTION, TxTypeFeeDelegatedSmartContractExecution::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_SMART_CONTRACT_EXECUTION_WITH_RATIO, TxTypeFeeDelegatedSmartContractExecutionWithRatio::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_VALUE_TRANSFER, TxTypeFeeDelegatedValueTransfer::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_VALUE_TRANSFER_MEMO, TxTypeFeeDelegatedValueTransferMemo::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_VALUE_TRANSFER_MEMO_WITH_RATIO, TxTypeFeeDelegatedValueTransferMemoWithRatio::decodeFromRawTransaction);
            put(TxType.Type.FEE_DELEGATED_VALUE_TRANSFER_WITH_RATIO, TxTypeFeeDelegatedValueTransferWithRatio::decodeFromRawTransaction);

            put(TxType.Type.ACCOUNT_UPDATE, TxTypeAccountUpdate::decodeFromRawTransaction);
            put(TxType.Type.VALUE_TRANSFER, TxTypeValueTransfer::decodeFromRawTransaction);
            put(TxType.Type.VALUE_TRANSFER_MEMO, TxTypeValueTransferMemo::decodeFromRawTransaction);
            put(TxType.Type.SMART_CONTRACT_DEPLOY, TxTypeSmartContractDeploy::decodeFromRawTransaction);
            put(TxType.Type.SMART_CONTRACT_EXECUTION, TxTypeSmartContractExecution::decodeFromRawTransaction);
            put(TxType.Type.CANCEL, TxTypeCancel::decodeFromRawTransaction);
            put(TxType.Type.CHAIN_DATA_ANCHORING, TxTypeChainDataAnchoringTransaction::decodeFromRawTransaction);
        }
    };

    public static AbstractTxType decode(String rawTransaction) {
        TxType.Type type = KlayTransactionUtils.getType(rawTransaction);
        return typeMap.get(type).apply(Numeric.hexStringToByteArray(rawTransaction));
    }
}
