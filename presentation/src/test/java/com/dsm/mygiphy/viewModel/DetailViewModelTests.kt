package com.dsm.mygiphy.viewModel

import com.dsm.domain.entity.GifDetailEntity
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetGifDetailUseCase
import com.dsm.domain.usecase.SetFavoriteUseCase
import com.dsm.mygiphy.BaseTest
import com.dsm.mygiphy.R
import com.dsm.mygiphy.createHttpException
import com.dsm.mygiphy.presentation.mapper.GifDetailModelMapper
import com.dsm.mygiphy.presentation.ui.detail.DetailViewModel
import com.jraska.livedata.test
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class DetailViewModelTests : BaseTest() {

    @Mock
    private lateinit var getGifDetailUseCase: GetGifDetailUseCase

    @Mock
    private lateinit var setFavoriteUseCase: SetFavoriteUseCase

    private val gifDetailModelMapper = GifDetailModelMapper()

    private lateinit var viewModel: DetailViewModel

    private val detailResponse = GifDetailEntity(
        id = "ID",
        gifUrl = "GIF_URL",
        avatarUrl = "AVATAR_URL",
        userName = "USER_NAME",
        displayName = "DISPLAY_NAME",
        isFavorite = true
    )

    @Before
    fun init() {
        viewModel = DetailViewModel(getGifDetailUseCase, setFavoriteUseCase, gifDetailModelMapper)
    }

    @Test
    fun getGifDetailSuccessTest() {
        `when`(getGifDetailUseCase.create("ID"))
            .thenReturn(Flowable.just(Resource.Success(detailResponse)))

        viewModel.getGifDetail("ID")

        viewModel.gifDetail.test().assertValue(gifDetailModelMapper.mapFrom(detailResponse))
    }

    @Test
    fun getGifDetailSuccessLocalTest() {
        `when`(getGifDetailUseCase.create("ID"))
            .thenReturn(Flowable.just(Resource.Success(detailResponse, true)))

        viewModel.getGifDetail("ID")

        viewModel.gifDetail.test().assertValue(gifDetailModelMapper.mapFrom(detailResponse))
        viewModel.snackbarRetryEvent.test().assertHasValue()
    }

    @Test
    fun getGifDetailFailedInternalTest() {
        `when`(getGifDetailUseCase.create("ID"))
            .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

        viewModel.getGifDetail("ID")

        viewModel.toastEvent.test().assertValue(R.string.fail_internal)
    }

    @Test
    fun getGifDetailFailedNotFoundTest() {
        `when`(getGifDetailUseCase.create("ID"))
            .thenReturn(Flowable.just(Resource.Error(ErrorEntity.NotFound(createHttpException(404)))))

        viewModel.getGifDetail("ID")

        viewModel.toastEvent.test().assertValue(R.string.fail_not_found)
    }

    @Test
    fun setFavoriteTest() {
        `when`(setFavoriteUseCase.create(SetFavoriteUseCase.Params("ID", true)))
            .thenReturn(Completable.complete())

        viewModel.isFavorite.value = false

        viewModel.onClickFavorite("ID")

        viewModel.toastEvent.test().assertValue(R.string.set_favorite)
        viewModel.isFavorite.test().assertValue(true)
    }

    @Test
    fun setUnfavoriteTest() {
        `when`(setFavoriteUseCase.create(SetFavoriteUseCase.Params("ID", false)))
            .thenReturn(Completable.complete())

        viewModel.isFavorite.value = true

        viewModel.onClickFavorite("ID")

        viewModel.toastEvent.test().assertValue(R.string.set_unfavorite)
        viewModel.isFavorite.test().assertValue(false)
    }
}