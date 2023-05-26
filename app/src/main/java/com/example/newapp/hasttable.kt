package com.example.newapp

class element(private var name: String,
              private var value: Int)
{
    public fun getName() : String
    {
        return name;
    }

    public fun setName(newName: String)
    {
        name = newName;
    }

    public fun getValue() : Int
    {
        return value;
    }

    public fun setValue(newValue: Int)
    {
        value = newValue;
    }
}

class hashTable(private var hashTable: HashSet<element> = hashSetOf()) {
    public fun getHashTable() : HashSet<element>
    {
        return hashTable;
    }

    public fun setHashTable(newHashTable: HashSet<element>)
    {
        hashTable = newHashTable;
    }

}