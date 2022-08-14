package guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: SelectBook
 * @Date: 2022/7/10 3:20
 */
public class Join {

    public static <R, C, V> Table<R, C, V> createTable(List<Map<C, V>> listMap, R id) {
        Table<R, C, V> table = HashBasedTable.create();
        for (Map<C, V> map : listMap) {
            for (Map.Entry<C, V> entry : map.entrySet()) {
                table.put((R) map.get(id), entry.getKey(), entry.getValue());
            }
        }
        return table;
    }

    public static <R, C, V> Table<R, C, V> join(Table<R, C, V> left, Table<R, C, V> right, JoinStrategy strategy) {
        switch (strategy) {
            case LEFT_JOIN:
                return leftJoin(left, right);
            case LEFT_SEMI_JOIN:
                return leftSemiJoin(left, right);
            case LEFT_ANTI_JOIN:
                return leftAntiJoin(left, right);
            case FULL_JOIN:
                return fullJoin(left, right);
            default:
                return innerJoin(left, right);
        }
    }

    /**
     * 未指定连接key的内连接，以rowKey作为连接键
     *
     * @param left
     * @param right
     * @return
     */
    private static <R, C, V> Table<R, C, V> innerJoin(Table<R, C, V> left, Table<R, C, V> right) {
        Table<R, C, V> result = HashBasedTable.create();
        Set<R> leftRowKey = Sets.newHashSet(left.rowKeySet());
        Set<R> rightRowKey = Sets.newHashSet(right.rowKeySet());
        leftRowKey.retainAll(rightRowKey);
        for (R key : leftRowKey) {
            Map<C, V> leftRow = left.row(key);
            Map<C, V> rightRow = right.row(key);
            for (Map.Entry<C, V> leftEntry : leftRow.entrySet()) {
                result.put(key, leftEntry.getKey(), leftEntry.getValue());
            }
            for (Map.Entry<C, V> rightEntry : rightRow.entrySet()) {
                result.put(key, rightEntry.getKey(), rightEntry.getValue());
            }
        }
        return result;
    }

    private static <R, C, V> Table<R, C, V> leftJoin(Table<R, C, V> left, Table<R, C, V> right) {
        Table<R, C, V> result = HashBasedTable.create();
        Set<R> leftRowKey = Sets.newHashSet(left.rowKeySet());
        for (R key : leftRowKey) {
            Map<C, V> leftRow = left.row(key);
            Map<C, V> rightRow = right.row(key);
            for (Map.Entry<C, V> leftEntry : leftRow.entrySet()) {
                result.put(key, leftEntry.getKey(), leftEntry.getValue());
                for (Map.Entry<C, V> rightEntry : rightRow.entrySet()) {
                    result.put(key, rightEntry.getKey(), rightEntry.getValue());
                }
            }
        }
        return result;
    }

    private static <R, C, V> Table<R, C, V> rightJoin(Table<R, C, V> left, Table<R, C, V> right) {
        return leftJoin(right, left);
    }

    private static <R, C, V> Table<R, C, V> leftSemiJoin(Table<R, C, V> left, Table<R, C, V> right) {
        Table<R, C, V> result = HashBasedTable.create();
        Set<R> leftRowKey = Sets.newHashSet(left.rowKeySet());
        Set<R> rightRowKey = Sets.newHashSet(right.rowKeySet());
        leftRowKey.retainAll(rightRowKey);
        for (R key : leftRowKey) {
            Map<C, V> leftRow = left.row(key);
            for (Map.Entry<C, V> leftEntry : leftRow.entrySet()) {
                result.put(key, leftEntry.getKey(), leftEntry.getValue());
            }
        }
        return result;
    }

    private static <R, C, V> Table<R, C, V> leftAntiJoin(Table<R, C, V> left, Table<R, C, V> right) {
        Table<R, C, V> result = HashBasedTable.create();
        Set<R> leftRowKey = Sets.newHashSet(left.rowKeySet());
        Set<R> rightRowKey = Sets.newHashSet(right.rowKeySet());
        for (R key : leftRowKey) {
            if (!rightRowKey.contains(key)) {
                Map<C, V> leftRow = left.row(key);
                for (Map.Entry<C, V> leftEntry : leftRow.entrySet()) {
                    result.put(key, leftEntry.getKey(), leftEntry.getValue());
                }
            }
        }
        return result;
    }

    private static <R, C, V> Table<R, C, V> fullJoin(Table<R, C, V> left, Table<R, C, V> right) {
        Table<R, C, V> result = HashBasedTable.create();
        Set<R> leftRowKey = Sets.newHashSet(left.rowKeySet());
        Set<R> rightRowKey = Sets.newHashSet(right.rowKeySet());
        Set<R> union = new HashSet<>(leftRowKey);
        union.addAll(rightRowKey);
        for (R key : union) {
            Map<C, V> leftRow = left.row(key);
            Map<C, V> rightRow = right.row(key);
            for (Map.Entry<C, V> leftEntry : leftRow.entrySet()) {
                result.put(key, leftEntry.getKey(), leftEntry.getValue());
            }
            for (Map.Entry<C, V> rightEntry : rightRow.entrySet()) {
                result.put(key, rightEntry.getKey(), rightEntry.getValue());
            }
        }
        return result;
    }
}
