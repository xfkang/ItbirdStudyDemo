package com.itbird.mvp.my;

/**
 * Created by itbird on 2022/2/25
 */
public interface IPresenter {
    void onAttach(IView view);

    void onDetach();
}
