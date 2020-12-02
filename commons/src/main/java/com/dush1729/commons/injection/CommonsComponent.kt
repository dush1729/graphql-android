package com.dush1729.commons.injection

import com.dush1729.repository.injection.RepositoryComponent
import dagger.Component

@PerActivity
@Component(dependencies = [RepositoryComponent::class])
interface CommonsComponent