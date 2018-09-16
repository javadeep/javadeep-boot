package com.javadeep.boot.common.dto;

import com.javadeep.boot.common.function.Operator2;
import com.javadeep.boot.common.util.CollectionFunction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * DTO相关支持
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class DTOSupport {

    private DTOSupport() {
        throw new UnsupportedOperationException();
    }

    /**
     * 将T2类型的信息补充到T1类型的数据中
     *
     * @param datas        T1类型的数据
     * @param idGetter     T1类型的ID获取函数
     * @param infoGetter   T2类型的信息获取函数
     * @param infoIdGetter T2类型的ID获取函数
     * @param infoSetter   将T2类型数据补充到T1类型数据中函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillOne(Collection<T1> datas,
                                            Function<? super T1, ? extends ID> idGetter,
                                            Function<? super Collection<ID>, ? extends List<T2>> infoGetter,
                                            Function<? super T2, ? extends ID> infoIdGetter,
                                            BiConsumer<? super T1, ? super T2> infoSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");

        List<ID> ids = datas.stream()
                .map(idGetter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        fillOneByIds(datas, ids, idGetter, infoGetter, infoIdGetter, infoSetter);
    }

    /**
     * 将T2类型的信息补充到T1类型的数据中，其中ids已经获取通过参数传入
     *
     * @param datas        T1类型的数据
     * @param ids          需要获取T2类型信息的ids列表
     * @param idGetter     T1类型的ID获取函数
     * @param infoGetter   T2类型的信息获取函数
     * @param infoIdGetter T2类型的ID获取函数
     * @param infoSetter   将T2类型数据补充到T1类型数据中函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillOneByIds(Collection<T1> datas,
                                                 Collection<ID> ids,
                                                 Function<? super T1, ? extends ID> idGetter,
                                                 Function<? super Collection<ID>, ? extends List<T2>> infoGetter,
                                                 Function<? super T2, ? extends ID> infoIdGetter,
                                                 BiConsumer<? super T1, ? super T2> infoSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }
        if (CollectionFunction.IS_EMPTY.apply(ids)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");
        Objects.requireNonNull(infoGetter, "infoGetter is null");
        Objects.requireNonNull(infoIdGetter, "infoIdGetter is null");
        Objects.requireNonNull(infoSetter, "infoSetter is null");

        List<T2> infos = infoGetter.apply(ids);
        if (CollectionFunction.IS_EMPTY.apply(infos)) {
            return;
        }
        Map<ID, T2> infoMap = infos.stream()
                .collect(Collectors.toMap(infoIdGetter, Function.identity(), Operator2.right()));

        for (T1 data : datas) {
            ID id = idGetter.apply(data);
            if (infoMap.containsKey(id)) {
                infoSetter.accept(data, infoMap.get(id));
            }
        }
    }

    /**
     * 将T2类型的信息补充到T1类型的数据中，其中T2类型的数据由字典获得
     *
     * @param datas        T1类型的数据
     * @param idGetter     T1类型的ID获取函数
     * @param dictSupplier 字典信息获取器
     * @param dictIdGetter 字典信息的ID获取函数
     * @param dictSetter   将字典信息补充到T1类型数据中的函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillOneByDict(Collection<T1> datas,
                                                  Function<? super T1, ? extends ID> idGetter,
                                                  Supplier<? extends List<T2>> dictSupplier,
                                                  Function<? super T2, ? extends ID> dictIdGetter,
                                                  BiConsumer<? super T1, ? super T2> dictSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");
        Objects.requireNonNull(dictSupplier, "dictSupplier is null");
        Objects.requireNonNull(dictIdGetter, "dictIdGetter is null");
        Objects.requireNonNull(dictSetter, "dictSetter is null");

        List<T2> dicts = dictSupplier.get();
        if (CollectionFunction.IS_EMPTY.apply(dicts)) {
            return;
        }

        Map<ID, T2> dictMap = dicts.stream()
                .collect(Collectors.toMap(dictIdGetter, Function.identity(), Operator2.right()));

        for (T1 data : datas) {
            ID id = idGetter.apply(data);
            if (dictMap.containsKey(id)) {
                dictSetter.accept(data, dictMap.get(id));
            }
        }
    }

    /**
     * 将T2类型的信息列表补充到T1类型的数据中
     *
     * @param datas        T1类型的数据
     * @param idGetter     T1类型的ID获取函数
     * @param infoGetter   T2类型的信息获取函数
     * @param infoIdGetter T2类型的ID获取函数
     * @param infoSetter   将T2类型数据列表补充到T1类型数据中函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillList(Collection<T1> datas,
                                             Function<? super T1, ? extends ID> idGetter,
                                             Function<? super Collection<ID>, ? extends List<T2>> infoGetter,
                                             Function<? super T2, ? extends ID> infoIdGetter,
                                             BiConsumer<? super T1, ? super List<T2>> infoSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");

        List<ID> ids = datas.stream()
                .map(idGetter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        fillListByIds(datas, ids, idGetter, infoGetter, infoIdGetter, infoSetter);
    }

    /**
     * 将T2类型的信息列表补充到T1类型的数据中，其中ids已经获取通过参数传入
     *
     * @param datas        T1类型的数据
     * @param ids          需要获取T2类型信息的ids列表
     * @param idGetter     T1类型的ID获取函数
     * @param infoGetter   T2类型的信息获取函数
     * @param infoIdGetter T2类型的ID获取函数
     * @param infoSetter   将T2类型数据列表补充到T1类型数据中函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillListByIds(Collection<T1> datas,
                                                 Collection<ID> ids,
                                                 Function<? super T1, ? extends ID> idGetter,
                                                 Function<? super Collection<ID>, ? extends List<T2>> infoGetter,
                                                 Function<? super T2, ? extends ID> infoIdGetter,
                                                 BiConsumer<? super T1, ? super List<T2>> infoSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }
        if (CollectionFunction.IS_EMPTY.apply(ids)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");
        Objects.requireNonNull(infoGetter, "infoGetter is null");
        Objects.requireNonNull(infoIdGetter, "infoIdGetter is null");
        Objects.requireNonNull(infoSetter, "infoSetter is null");

        List<T2> infos = infoGetter.apply(ids);
        if (CollectionFunction.IS_EMPTY.apply(infos)) {
            return;
        }
        Map<ID, List<T2>> infoMap = infos.stream()
                .collect(Collectors.groupingBy(infoIdGetter));

        for (T1 data : datas) {
            ID id = idGetter.apply(data);
            if (infoMap.containsKey(id)) {
                infoSetter.accept(data, infoMap.get(id));
            }
        }
    }

    /**
     * 将T2类型的信息列表补充到T1类型的数据中，其中T2类型的数据由字典获得
     *
     * @param datas        T1类型的数据
     * @param idGetter     T1类型的ID获取函数
     * @param dictSupplier 字典信息获取器
     * @param dictIdGetter 字典信息的ID获取函数
     * @param dictSetter   将字典信息列表补充到T1类型数据中的函数
     * @param <T1>         T1数据类型
     * @param <ID>         ID数据类型（连接T1类型和T2类型的数据的字段类型）
     * @param <T2>         T2数据类型
     */
    public static <T1, ID, T2> void fillListByDict(Collection<T1> datas,
                                                  Function<? super T1, ? extends ID> idGetter,
                                                  Supplier<? extends List<T2>> dictSupplier,
                                                  Function<? super T2, ? extends ID> dictIdGetter,
                                                  BiConsumer<? super T1, ? super List<T2>> dictSetter) {
        if (CollectionFunction.IS_EMPTY.apply(datas)) {
            return;
        }

        Objects.requireNonNull(idGetter, "idGetter is null");
        Objects.requireNonNull(dictSupplier, "dictSupplier is null");
        Objects.requireNonNull(dictIdGetter, "dictIdGetter is null");
        Objects.requireNonNull(dictSetter, "dictSetter is null");

        List<T2> dicts = dictSupplier.get();
        if (CollectionFunction.IS_EMPTY.apply(dicts)) {
            return;
        }

        Map<ID, List<T2>> dictMap = dicts.stream()
                .collect(Collectors.groupingBy(dictIdGetter));

        for (T1 data : datas) {
            ID id = idGetter.apply(data);
            if (dictMap.containsKey(id)) {
                dictSetter.accept(data, dictMap.get(id));
            }
        }
    }
}