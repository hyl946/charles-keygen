package com.xk72.charles;

public class CharlesKeygenMagic {

    private CharlesKeygenMagic() {

    }

    public static void checkPrefix(String name, String license) {
        long key2 = Long.parseLong(license.substring(2, 10), 16) << 32 | Long.parseLong(license.substring(10, 18), 16);
        int key1 = Integer.parseInt(license.substring(0, 2), 16);
        long key = new SimpleRC5(CharlesKeygen.RC5KEY_KEY).encrypt(key2);
        if (key1 == CharlesKeygen.xor(key)) {
            System.out.format("Key: %s, version: %d, extra: %06x, namePrefix: 0x%08x, name: %s%n",
                    license, (key >>> 24) & 0xffL, key & 0xffffffL,
                    name != null ? (int) ((key >>> 32)) ^ CharlesKeygen.calcPrefix(name) : 0, name);
        } else {
            System.err.format("unsupported %s / %s%n", name, license);
        }
    }

    public static void main(String[] args) {
        checkPrefix("anthony ortolani", "a4036b2761c9583fda");
        checkPrefix("http://ninjasaga.cheat.center", "18e69f6d5bc820d4d3");
    }

}
