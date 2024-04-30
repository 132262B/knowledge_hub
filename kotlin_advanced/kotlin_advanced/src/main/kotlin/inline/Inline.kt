package inline

class Inline {
}

fun main() {
//    repeat(2) {
//        println("Hello")
//    }

    iterate(listOf(1, 2, 3, 4, 5)) {
        if(it == 3) {
            return@iterate
        }
        println(it)
    }
}

inline fun repeat(times: Int, action: () -> Unit) {
    for (i in 0 until times) {
        action()
    }
}

inline fun iterate(numbers : List<Int>, crossinline exec : (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

// inline 전
//public final class InlineKt {
//    public static final void main() {
//        repeat(2, (Function0)null.INSTANCE);
//    }
//
//    // $FF: synthetic method
//    public static void main(String[] var0) {
//        main();
//    }
//
//    public static final void repeat(int times, @NotNull Function0 action) {
//        Intrinsics.checkNotNullParameter(action, "action");
//        int var2 = 0;
//
//        for(int var3 = times; var2 < var3; ++var2) {
//            action.invoke();
//        }
//
//    }
//}

// inline 후

//public final class InlineKt {
//    public static final void main() {
//        int times$iv = 2;
//        int $i$f$repeat = false;
//        int var2 = 0;
//
//        for(byte var3 = times$iv; var2 < var3; ++var2) {
//            int var4 = false;
//            String var5 = "Hello";
//            System.out.println(var5);
//        }
//
//    }
//
//    // $FF: synthetic method
//    public static void main(String[] var0) {
//        main();
//    }
//
//    public static final void repeat(int times, @NotNull Function0 action) {
//        int $i$f$repeat = 0;
//        Intrinsics.checkNotNullParameter(action, "action");
//        int var3 = 0;
//
//        for(int var4 = times; var3 < var4; ++var3) {
//            action.invoke();
//        }
//
//    }
//}
