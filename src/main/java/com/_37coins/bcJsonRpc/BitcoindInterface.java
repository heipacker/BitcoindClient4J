package com._37coins.bcJsonRpc;

import com._37coins.bcJsonRpc.pojo.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BitcoindInterface {

    /**
     * Add a nrequired-to-sign multisignature address to the wallet. Each key is a bitcoin address or hex-encoded public key.
     *
     * @param nrequired
     * @param keys
     * @return String
     */
    String addmultisigaddress(int nrequired, String keys);

    /**
     * If [account] is specified, assign address to [account].
     *
     * @param nrequired
     * @param keys
     * @param account
     * @return String
     */
    String addmultisigaddress(int nrequired, String keys, String account);

    /**
     * Returns an object containing various state info.
     *
     * @return Info
     */
    Info getinfo();

    /**
     * Safely copies wallet.dat to destination, which can be a directory or a path with filename.
     *
     * @return boolean
     */
    boolean backupwallet();

    /**
     * Removes the wallet encryption key from memory, locking the wallet. After calling this method, you will need to call walletpassphrase again before being able to call any methods which require the wallet to be unlocked.
     */
    void walletlock();

    /**
     * rounded to the nearest 0.00000001
     *
     * @param fee
     * @return boolean
     */
    boolean settxfee(BigDecimal fee);

    /**
     * Sets the account associated with the given address. Assigning address that is already assigned to the same account will create a new address associated with that account.
     *
     * @param bitcoinAddress
     * @param accountLabel
     * @return boolean
     */
    boolean setaccount(String bitcoinAddress, String accountLabel);

    /**
     * Returns the account associated with the given address.
     *
     * @param bitcoinAddress
     * @return String
     */
    String getaccount(String bitcoinAddress);

    /**
     * Returns the current bitcoin address for receiving payments to this account.
     *
     * @param accountLabel
     * @return String
     */
    String getaccountaddress(String accountLabel);

    /**
     * Returns the list of addresses for the given account.
     *
     * @param accountLabel
     * @return List<String>
     */
    List<String> getaddressesbyaccount(String accountLabel);

    /**
     * If [account] is not specified, returns the server's total available balance.
     *
     * @return BigDecimal
     */
    BigDecimal getbalance();

    /**
     * If [account] is specified, returns the balance in the account.
     *
     * @param account
     * @return BigDecimal
     */
    BigDecimal getbalance(String account);

    /**
     * @param account
     * @param minimumConfirmations
     * @return BigDecimal
     */
    BigDecimal getbalance(String account, int minimumConfirmations);

    /**
     * Returns information about the block with the given hash.
     *
     * @param blockHash
     * @return Block
     */
    Block getblock(String blockHash);

    /**
     * Returns the number of blocks in the longest block chain.
     *
     * @return long
     */
    long getblockcount();

    /**
     * Returns hash of block in best-block-chain at <index>; index 0 is the genesis block
     *
     * @param blockHeight
     * @return String
     */
    String getblockhash(long blockHeight);

    /**
     * Returns the number of connections to other nodes.
     *
     * @return int
     */
    int getconnectioncount();

    /**
     * Returns the proof-of-work difficulty as a multiple of the minimum difficulty.
     *
     * @return BigDecimal
     */
    BigDecimal getdifficulty();

    /**
     * Returns true or false whether bitcoind is currently generating hashes
     *
     * @return boolean
     */
    boolean getgenerate();

    /**
     * Returns a recent hashes per second performance measurement while generating.
     *
     * @return long
     */
    long gethashespersec();

    /**
     * Returns an object about the given transaction hash.
     *
     * @param hash
     * @return Transaction
     */
    Transaction gettransaction(String hash);

    /**
     * Returns Object that has account names as keys, account balances as values.
     *
     * @param confirmations
     * @return Map<String, BigDecimal>
     */
    Map<String, BigDecimal> listaccounts(long confirmations);

    /**
     * Returns an array of objects containing:"account" : the account of the receiving addresses,"amount" : total amount received by addresses with this account,"confirmations" : number of confirmations of the most recent transaction included
     *
     * @param minConfirmations
     * @param includeEmpty
     * @return List<Account>
     */
    List<Account> listreceivedbyaccount(long minConfirmations, boolean includeEmpty);

    /**
     * Returns an array of objects containing:"address" : receiving address,"account" : the account of the receiving address,"amount" : total amount received by the address,"confirmations" : number of confirmations of the most recent transaction included,To get a list of accounts on the system, execute bitcoind listreceivedbyaddress 0 true
     *
     * @param minConfirmations
     * @param includeEmpty
     * @return
     */
    List<Address> listreceivedbyaddress(long minConfirmations, boolean includeEmpty);

    /**
     * Get all transactions in blocks since block [blockhash], or all transactions if omitted.
     *
     * @param blockhash
     * @param minConfirmations
     * @return
     */
    List<LastBlock> listsinceblock(String blockhash, int minConfirmations);

    /**
     * Returns up to [count] most recent transactions skipping the first [from] transactions for account [account]. If [account] not provided will return recent transaction from all accounts.
     *
     * @param account
     * @param count
     * @param offset
     * @return
     */
    List<Transaction> listtransactions(String account, int count, int offset);

    /**
     * Import a private key into your bitcoin wallet. Private key must be in wallet import format (Sipa) beginning with a '5'.
     *
     * @param privateKey
     * @return
     */
    boolean importprivkey(String privateKey);

    /**
     * Move funds from one account in your wallet to another.
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return
     */
    boolean move(String fromAccount, String toAccount, BigDecimal amount);

    /**
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @param minconf
     * @param comment
     * @return
     */
    boolean move(String fromAccount, String toAccount, BigDecimal amount, long minconf, String comment);

    /**
     * amount is a real and is rounded to 8 decimal places. Will send the given amount to the given address, ensuring the account has a valid balance using [minconf] confirmations. Returns the transaction ID if successful (not in JSON object).
     *
     * @param fromAccount
     * @param bitcoinAddress
     * @param amount
     * @return
     */
    String sendfrom(String fromAccount, String bitcoinAddress, BigDecimal amount);

    /**
     * @param fromAccount
     * @param bitcoinAddress
     * @param amount
     * @param minconf
     * @param comment
     * @param commentTo
     * @return
     */
    String sendfrom(String fromAccount, String bitcoinAddress, BigDecimal amount, long minconf, String comment, String commentTo);

    /**
     * amounts are BigDecimal-precision floating point numbers.
     *
     * @param fromAccount
     * @param addressAmountPairs
     * @return
     */
    String sendmany(String fromAccount, Map<String, BigDecimal> addressAmountPairs);

    /**
     * amounts are BigDecimal-precision floating point numbers.
     *
     * @param fromAccount
     * @param addressAmountPairs
     * @param minconf
     * @param comment
     * @return
     */
    String sendmany(String fromAccount, Map<String, BigDecimal> addressAmountPairs, int minconf, String comment);

    /**
     * amount is a real and is rounded to 8 decimal places. Returns the transaction hash if successful.
     *
     * @param bitcoinAddress
     * @param amount
     * @return
     */
    String sendtoaddress(String bitcoinAddress, BigDecimal amount);

    /**
     * @param generate
     */
    void setgenerate(boolean generate);

    /**
     * @param generate
     * @param genproclimit
     */
    void setgenerate(boolean generate, int genproclimit);

    /**
     * Return information about bitcoinaddress.
     *
     * @param bitcoinAddress
     * @return
     */
    AddressInformation validateaddress(String bitcoinAddress);

    /**
     * Returns a new bitcoin address for receiving payments. If [account] is specified (recommended), it is added to the address book so payments received with the address will be credited to [account].
     *
     * @param label
     * @return
     */
    String getnewaddress(String label);

    /**
     * Returns a Base64 encoded signature used to verify the provided message was signed by the owner of bitcoinaddress
     *
     * @param bitcoinaddress
     * @param message
     * @return
     */
    String signmessage(String bitcoinaddress, String message);

    /**
     * Verifies the signature and message matches the bitcoin address provided (See signmessage)
     *
     * @param bitcoinaddress
     * @param signature
     * @param message
     * @return
     */
    boolean verifymessage(String bitcoinaddress, String signature, String message);

    /**
     * stop
     *
     * @return
     */
    String stop();

}
