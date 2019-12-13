package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Asset extends Contract {
    public static String BINARY = "60806040523480156200001157600080fd5b506200002b62000031640100000000026401000000009004565b6200018c565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260078152602001807f745f617373657400000000000000000000000000000000000000000000000000815250602001848103835260078152602001807f6163636f756e74000000000000000000000000000000000000000000000000008152506020018481038252600b8152602001807f61737365745f76616c75650000000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b1580156200014b57600080fd5b505af115801562000160573d6000803e3d6000fd5b505050506040513d60208110156200017757600080fd5b81019080805190602001909291905050505050565b612453806200019c6000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631de45b10146100675780635116063014610134578063e2a1a99d1461015f578063fcd7e3c1146101e6575b600080fd5b34801561007357600080fd5b5061011e600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192908035906020019092919050505061026a565b6040518082815260200191505060405180910390f35b34801561014057600080fd5b50610149611254565b6040518082815260200191505060405180910390f35b34801561016b57600080fd5b506101d0600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506117cc565b6040518082815260200191505060405180910390f35b3480156101f257600080fd5b5061024d600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611e29565b604051808381526020018281526020019250505060405180910390f35b60008060008060008060008060008097506000965060009550600094506102908c611e29565b80975081985050506000871415156103e6577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff97507f3a5c0c275bb95165abf2ffef5511a8c0bdd80dedb12a4e92960483b73aaee8a7888d8d8d604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b8381101561033a57808201518184015260208101905061031f565b50505050905090810190601f1680156103675780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156103a0578082015181840152602081019050610385565b50505050905090810190601f1680156103cd5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1879850611245565b6103ef8b611e29565b8096508198505050600087141515610545577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe97507f3a5c0c275bb95165abf2ffef5511a8c0bdd80dedb12a4e92960483b73aaee8a7888d8d8d604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b8381101561049957808201518184015260208101905061047e565b50505050905090810190601f1680156104c65780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156104ff5780820151818401526020810190506104e4565b50505050905090810190601f16801561052c5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1879850611245565b848a86011215610693577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffc97507f3a5c0c275bb95165abf2ffef5511a8c0bdd80dedb12a4e92960483b73aaee8a7888d8d8d604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b838110156105e75780820151818401526020810190506105cc565b50505050905090810190601f1680156106145780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b8381101561064d578082015181840152602081019050610632565b50505050905090810190601f16801561067a5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1879850611245565b61069b612293565b93508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561070157600080fd5b505af1158015610715573d6000803e3d6000fd5b505050506040513d602081101561072b57600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663e942b5168d6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f6163636f756e7400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156107fe5780820151818401526020810190506107e3565b50505050905090810190601f16801561082b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561084b57600080fd5b505af115801561085f573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff16632ef8ba748b88036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f61737365745f76616c756500000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561090d57600080fd5b505af1158015610921573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18d858773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109a757600080fd5b505af11580156109bb573d6000803e3d6000fd5b505050506040513d60208110156109d157600080fd5b81019080805190602001909291905050506040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015610ab1578082015181840152602081019050610a96565b50505050905090810190601f168015610ade5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b158015610aff57600080fd5b505af1158015610b13573d6000803e3d6000fd5b505050506040513d6020811015610b2957600080fd5b81019080805190602001909291905050509150600182141515610c8a577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffb97507f3a5c0c275bb95165abf2ffef5511a8c0bdd80dedb12a4e92960483b73aaee8a7888d8d8d604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b83811015610bde578082015181840152602081019050610bc3565b50505050905090810190601f168015610c0b5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b83811015610c44578082015181840152602081019050610c29565b50505050905090810190601f168015610c715780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1879850611245565b8373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610cee57600080fd5b505af1158015610d02573d6000803e3d6000fd5b505050506040513d6020811015610d1857600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff1663e942b5168c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f6163636f756e7400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610deb578082015181840152602081019050610dd0565b50505050905090810190601f168015610e185780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610e3857600080fd5b505af1158015610e4c573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff16632ef8ba748b87016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f61737365745f76616c756500000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610efa57600080fd5b505af1158015610f0e573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18c838773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610f9457600080fd5b505af1158015610fa8573d6000803e3d6000fd5b505050506040513d6020811015610fbe57600080fd5b81019080805190602001909291905050506040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b8381101561109e578082015181840152602081019050611083565b50505050905090810190601f1680156110cb5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b1580156110ec57600080fd5b505af1158015611100573d6000803e3d6000fd5b505050506040513d602081101561111657600080fd5b8101908080519060200190929190505050507f3a5c0c275bb95165abf2ffef5511a8c0bdd80dedb12a4e92960483b73aaee8a7888d8d8d604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b8381101561119d578082015181840152602081019050611182565b50505050905090810190601f1680156111ca5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156112035780820151818401526020810190506111e8565b50505050905090810190601f1680156112305780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a18798505b50505050505050509392505050565b600080600080611262612293565b9250600091505b60028054905082101561178a578273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156112da57600080fd5b505af11580156112ee573d6000803e3d6000fd5b505050506040513d602081101561130457600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff1663e942b51660028481548110151561134257fe5b906000526020600020016040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f6163636f756e74000000000000000000000000000000000000000000000000008152506020018381038252848181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156114385780601f1061140d57610100808354040283529160200191611438565b820191906000526020600020905b81548152906001019060200180831161141b57829003601f168201915b50509350505050600060405180830381600087803b15801561145957600080fd5b505af115801561146d573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff16632ef8ba7460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f61737365745f76616c756500000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561151a57600080fd5b505af115801561152e573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663bf2b70a160028481548110151561155d57fe5b90600052602060002001838673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156115cc57600080fd5b505af11580156115e0573d6000803e3d6000fd5b505050506040513d60208110156115f657600080fd5b81019080805190602001909291905050506040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182810382528581815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561171f5780601f106116f45761010080835404028352916020019161171f565b820191906000526020600020905b81548152906001019060200180831161170257829003601f168201915b5050945050505050602060405180830381600087803b15801561174157600080fd5b505af1158015611755573d6000803e3d6000fd5b505050506040513d602081101561176b57600080fd5b8101908080519060200190929190505050508180600101925050611269565b7fe52aa7342eb68231147c9ade67e21ba8108f3def0d92c4f501c9c8953b761c8260006040518082815260200191505060405180910390a16000935050505090565b600080600080600080600080955060009450600093506117eb89611e29565b8095508196505050600085141515611d4a57611805612293565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561186b57600080fd5b505af115801561187f573d6000803e3d6000fd5b505050506040513d602081101561189557600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f6163636f756e7400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561196857808201518184015260208101905061194d565b50505050905090810190601f1680156119955780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156119b557600080fd5b505af11580156119c9573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f61737365745f76616c756500000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015611a7557600080fd5b505af1158015611a89573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac368a846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015611b48578082015181840152602081019050611b2d565b50505050905090810190601f168015611b755780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015611b9557600080fd5b505af1158015611ba9573d6000803e3d6000fd5b505050506040513d6020811015611bbf57600080fd5b810190808051906020019092919050505090506001811415611d2157600095503360008a6040518082805190602001908083835b602083101515611c185780518252602082019150602081019050602083039250611bf3565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555088600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190611cdc929190612382565b506002899080600181540180825580915050906001820390600052602060002001600090919290919091509080519060200190611d1a929190612382565b5050611d45565b7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe95505b611d6e565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff95505b7fc80814306a567881c5c316a4db1321b1f7c3d59f2c0c3baa0dd2013cdef90f34868a8a6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b83811015611dde578082015181840152602081019050611dc3565b50505050905090810190601f168015611e0b5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a185965050505050505092915050565b600080600080600080611e3a612293565b93508373ffffffffffffffffffffffffffffffffffffffff1663e8434e39888673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611ebd57600080fd5b505af1158015611ed1573d6000803e3d6000fd5b505050506040513d6020811015611ee757600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015611f95578082015181840152602081019050611f7a565b50505050905090810190601f168015611fc25780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015611fe257600080fd5b505af1158015611ff6573d6000803e3d6000fd5b505050506040513d602081101561200c57600080fd5b81019080805190602001909291905050509250600091508273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561208757600080fd5b505af115801561209b573d6000803e3d6000fd5b505050506040513d60208110156120b157600080fd5b8101908080519060200190929190505050600014156120f8577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff828191509550955061228a565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561216857600080fd5b505af115801561217c573d6000803e3d6000fd5b505050506040513d602081101561219257600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600b8152602001807f61737365745f76616c7565000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561224757600080fd5b505af115801561225b573d6000803e3d6000fd5b505050506040513d602081101561227157600080fd5b8101908080519060200190929190505050819150955095505b50505050915091565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f745f617373657400000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561233d57600080fd5b505af1158015612351573d6000803e3d6000fd5b505050506040513d602081101561236757600080fd5b81019080805190602001909291905050509050809250505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106123c357805160ff19168380011785556123f1565b828001600101855582156123f1579182015b828111156123f05782518255916020019190600101906123d5565b5b5090506123fe9190612402565b5090565b61242491905b80821115612420576000816000905550600101612408565b5090565b905600a165627a7a723058200dcd7f0ac191a4ae48a5b2e7bdcb0fa97fa7f11889dec999df54b4db273c3d5c0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"from_account\",\"type\":\"string\"},{\"name\":\"to_account\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"settlement\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"string\"},{\"name\":\"asset_value\",\"type\":\"int256\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"asset_value\",\"type\":\"int256\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"from_account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"to_account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"TransferEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"}],\"name\":\"SettlementEvent\",\"type\":\"event\"}]";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_SETTLEMENT = "settlement";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_SELECT = "select";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event TRANSFEREVENT_EVENT = new Event("TransferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event SETTLEMENTEVENT_EVENT = new Event("SettlementEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> transfer(String from_account, String to_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String from_account, String to_account, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferSeq(String from_account, String to_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> settlement() {
        final Function function = new Function(
                FUNC_SETTLEMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void settlement(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETTLEMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String settlementSeq() {
        final Function function = new Function(
                FUNC_SETTLEMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> register(String account, BigInteger asset_value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String account, BigInteger asset_value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerSeq(String account, BigInteger asset_value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> select(String account) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.asset_value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRegisterEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRegisterEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to_account = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<SettlementEventEventResponse> getSettlementEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SETTLEMENTEVENT_EVENT, transactionReceipt);
        ArrayList<SettlementEventEventResponse> responses = new ArrayList<SettlementEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SettlementEventEventResponse typedResponse = new SettlementEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSettlementEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETTLEMENTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerSettlementEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETTLEMENTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String account;

        public BigInteger asset_value;
    }

    public static class TransferEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String from_account;

        public String to_account;

        public BigInteger amount;
    }

    public static class SettlementEventEventResponse {
        public Log log;

        public BigInteger ret;
    }
}
