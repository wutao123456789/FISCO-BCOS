pragma solidity ^0.4.24;

import "./Table.sol";

contract Asset {

    event RegisterEvent(int256 ret, string account, int256 asset_value);
    event TransferEvent(int256 ret, string from_account, string to_account, int256 amount);
    event SettlementEvent(int256 ret);
    
    mapping(string=>address) str_addr;
    mapping(address=>string) addr_str;
    string[] accountList;

    constructor() public {

        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 

        tf.createTable("t_asset", "account", "asset_value");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_asset");
        return table;
    }

    function select(string account) public constant returns(int256, int256) {

        Table table = openTable();

        Entries entries = table.select(account, table.newCondition());
        int256 asset_value = 0;
        if (0 == int256(entries.size())) {
            return (-1, asset_value);
        } else {
            Entry entry = entries.get(0);
            return (0, int256(entry.getInt("asset_value")));
        }
    }


    function register(string account, int256 asset_value) public returns(int256){
        int256 ret_code = 0;
        int256 ret= 0;
        int256 temp_asset_value = 0;

        (ret, temp_asset_value) = select(account);
        if(ret != 0) {
            Table table = openTable();
            
            Entry entry = table.newEntry();
            entry.set("account", account);
            entry.set("asset_value", int256(asset_value));


            int count = table.insert(account, entry);
            if (count == 1) {

                ret_code = 0;
                str_addr[account] = msg.sender;
                addr_str[msg.sender] = account;
                accountList.push(account);
            } else {

                ret_code = -2;
            }
        } else {

            ret_code = -1;
        }

        emit RegisterEvent(ret_code, account, asset_value);

        return ret_code;
    }

    function transfer(string from_account, string to_account, int256 amount) public returns(int256) {

        int ret_code = 0;
        int256 ret = 0;
        int256 from_asset_value = 0;
        int256 to_asset_value = 0;
        

        (ret, from_asset_value) = select(from_account);
        if(ret != 0) {
            ret_code = -1;

            emit TransferEvent(ret_code, from_account, to_account, amount);
            return ret_code;

        }


        (ret, to_asset_value) = select(to_account);
        if(ret != 0) {
            ret_code = -2;

            emit TransferEvent(ret_code, from_account, to_account, amount);
            return ret_code;
        } 

        if (to_asset_value + amount < to_asset_value) {
            ret_code = -4;

            emit TransferEvent(ret_code, from_account, to_account, amount);
            return ret_code;
        }

        Table table = openTable();

        Entry entry0 = table.newEntry();
        entry0.set("account", from_account);
        entry0.set("asset_value", int256(from_asset_value - amount));

        int count = table.update(from_account, entry0, table.newCondition());
        if(count != 1) {
            ret_code = -5;

            emit TransferEvent(ret_code, from_account, to_account, amount);
            return ret_code;
        }

        Entry entry1 = table.newEntry();
        entry1.set("account", to_account);
        entry1.set("asset_value", int256(to_asset_value + amount));

        table.update(to_account, entry1, table.newCondition());

        emit TransferEvent(ret_code, from_account, to_account, amount);

        return ret_code;
    }

    function settlement() public returns(int256){
        Table table = openTable();

        for(uint i = 0; i < accountList.length; i++){

            Entry entry = table.newEntry();
            entry.set("account", accountList[i]);
            entry.set("asset_value", int256(0));

            table.update(accountList[i], entry, table.newCondition());
        }

        emit SettlementEvent(0);
        return 0;
    }
}

