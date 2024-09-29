package com.abrebo.rfootballclubrfc.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.abrebo.rfootballclubrfc.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MyListViewModel @Inject constructor(var repository: Repository) :ViewModel() {



}